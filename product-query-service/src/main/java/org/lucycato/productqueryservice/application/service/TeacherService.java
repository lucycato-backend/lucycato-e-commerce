package org.lucycato.productqueryservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.productqueryservice.application.port.in.TeacherUseCase;
import org.lucycato.productqueryservice.application.port.in.command.SpecificTeacherCourseSeriesSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.TeacherCourseSeriesSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.TeacherDetailSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.TeacherSearchCommand;
import org.lucycato.productqueryservice.application.port.out.*;
import org.lucycato.productqueryservice.application.port.out.result.CheckedRecentCourseOpenResult;
import org.lucycato.productqueryservice.application.port.out.result.CheckedRecentTeacherNewsResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesResult;
import org.lucycato.productqueryservice.domain.Teacher;
import org.lucycato.productqueryservice.domain.TeacherCourseSeries;
import org.lucycato.productqueryservice.domain.TeacherDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService implements TeacherUseCase {
    private final TeacherPort teacherPort;

    private final CourseSeriesPort courseSeriesPort;

    private final CoursePort coursePort;

    private final TextEBookPort textEBookPort;

    private final BoardPort boardPort;

    /*
    TODO: 여러번의 I/O를 발생시키지 않아서 성능의 문제를 해결
    1. Network I/O 한번만 발생
    2. Blocking 발생 X, Non Blocking 처리
    3. flatMap + ConcurrentHashMap 사용
    4. Join vs Async
    5. Cash 적극 활용 (1:N, 1:1, N:1)
    6. 수직 + 수평 Async 고려
    */
    /*
    MVC
    1. Network I/O를 한번만 발생
        - N : 1 문제 해결
     */
    @Override
    public Flux<Teacher> getTeacherList(TeacherSearchCommand command) {
        Map<Long, CheckedRecentCourseOpenResult> courseMap = new ConcurrentHashMap<>();
        Map<Long, CheckedRecentTeacherNewsResult> newsMap = new ConcurrentHashMap<>();

        Mono<Void> process1 = teacherPort.getTeacherIdsByTeachingGenre(command.getTeachingGenre())
                .collectList()
                .flatMapMany(coursePort::checkRecentCourseOpenListByTeacherIds)
                .flatMap(item -> {
                    courseMap.put(item.getTeacherId(), item);
                    return Flux::just;
                })
                .then();

        Mono<Void> process2 = teacherPort.getTeacherIdsByTeachingGenre(command.getTeachingGenre())
                .collectList()
                .flatMapMany(boardPort::checkTeacherNewsListByTeacherIds)
                .flatMap(item -> {
                    newsMap.put(item.getTeacherId(), item);
                    return Flux::just;
                })
                .then();

        return Flux.combineLatest(
                        Mono.when(process1, process2).flatMapMany(Flux::just),
                        teacherPort.getTeacherListByTeachingGenre(command.getTeachingGenre()),
                        (a, b) -> b
                )
                .flatMap(teacherResult -> Flux.just(
                        Teacher.from(
                                teacherResult,
                                Optional.ofNullable(courseMap.get(teacherResult.getTeacherId()))
                                        .map(CheckedRecentCourseOpenResult::getIsRecentCourseOpen)
                                        .orElse(false),
                                Optional.ofNullable(newsMap.get(teacherResult.getTeacherId()))
                                        .map(CheckedRecentTeacherNewsResult::getIsRecentTeacherNews)
                                        .orElse(false)
                        )))
                .sort((a, b) -> Long.compare(b.getTeacherId(), a.getTeacherId()));
    }

    @Override
    public Mono<TeacherDetail> getTeacher(TeacherDetailSearchCommand command) {
        if (command.getIsSimple()) {
            return Mono.zip(
                            coursePort.checkRecentCourseOpenListByTeacherIds(Collections.singletonList(command.getTeacherId())).collectList(),
                            boardPort.checkTeacherNewsListByTeacherIds(Collections.singletonList(command.getTeacherId())).collectList()
                    )
                    .flatMap(tuples ->
                            teacherPort.getTeacher(command.getTeacherId())
                                    .flatMap(teacherResult -> Mono.just(
                                            TeacherDetail.simple(
                                                    teacherResult,
                                                    !tuples.getT1().isEmpty() && tuples.getT1().get(0).getIsRecentCourseOpen(),
                                                    !tuples.getT2().isEmpty() && tuples.getT1().get(0).getIsRecentCourseOpen()
                                            )
                                    ))
                    );
        } else {
            return Mono.zip(
                            coursePort.checkRecentCourseOpenListByTeacherIds(Collections.singletonList(command.getTeacherId())).collectList(),
                            boardPort.checkTeacherNewsListByTeacherIds(Collections.singletonList(command.getTeacherId())).collectList(),
                            courseSeriesPort.getCourseSeriesCount(),
                            coursePort.getCourseCount(),
                            textEBookPort.getTextEBookCountResult(),
                            boardPort.getCourseReviewCount(),
                            boardPort.countTeacherNoticeCount()
                    )
                    .flatMap(tuples ->
                            teacherPort.getTeacher(command.getTeacherId())
                                    .flatMap(teacherDetailResult -> Mono.just(
                                            TeacherDetail.from(
                                                    teacherDetailResult,
                                                    !tuples.getT1().isEmpty() && tuples.getT1().get(0).getIsRecentCourseOpen(),
                                                    !tuples.getT2().isEmpty() && tuples.getT1().get(0).getIsRecentCourseOpen(),
                                                    tuples.getT3(),
                                                    tuples.getT4(),
                                                    tuples.getT5(),
                                                    tuples.getT6(),
                                                    tuples.getT7()
                                            )
                                    ))
                    );
        }
    }

    @Override
    public Flux<TeacherCourseSeries> getTeacherCourseSeriesList(TeacherCourseSeriesSearchCommand command) {
        Map<Long, CourseSeriesResult> map = new ConcurrentHashMap<>();

        Mono<Void> process = teacherPort.getTeacherIdsByTeachingGenre(command.getTeachingGenre())
                .collectList()
                .flatMapMany(courseSeriesPort::getCourseSeriesListByTeacherIds)
                .flatMap(item -> {
                    map.put(item.getTeacherId(), item);
                    return Flux::just;
                })
                .then();

        return Flux.combineLatest(
                        Mono.when(process).flatMapMany(Flux::just),
                        teacherPort.getTeacherListByTeachingGenre(command.getTeachingGenre()),
                        (a, b) -> b
                )
                .flatMap(teacherResult -> Flux.just(TeacherCourseSeries.from(teacherResult, map.get(teacherResult.getTeacherId()))))
                .sort(Collections.reverseOrder());

    }

    @Override
    public Flux<TeacherCourseSeries> getTeacherCourseSeriesList(SpecificTeacherCourseSeriesSearchCommand command) {
        return teacherPort.getSimpleTeacher(command.getTeacherId())
                .flatMapMany(teacherResult ->
                        courseSeriesPort.getCourseSeriesListByTeacherIds(Collections.singletonList(teacherResult.getTeacherId()))
                                .flatMap(courseSeriesResult ->
                                        Flux.just(TeacherCourseSeries.from(teacherResult, courseSeriesResult))
                                )
                )
                .sort(Collections.reverseOrder());
    }
}
