package org.lucycato.productqueryservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productqueryservice.application.port.in.TeacherUseCase;
import org.lucycato.productqueryservice.application.port.in.command.SpecificTeacherCourseSeriesSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.TeacherCourseSeriesSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.TeacherDetailSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.TeacherSearchCommand;
import org.lucycato.productqueryservice.application.port.out.*;
import org.lucycato.productqueryservice.application.port.out.result.CheckedRecentCourseOpenResult;
import org.lucycato.productqueryservice.application.port.out.result.CheckedRecentTeacherNoticeResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesResult;
import org.lucycato.productqueryservice.application.port.out.result.TeacherResult;
import org.lucycato.productqueryservice.domain.Teacher;
import org.lucycato.productqueryservice.domain.TeacherCourseSeries;
import org.lucycato.productqueryservice.domain.TeacherDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
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
        Map<Long, CheckedRecentTeacherNoticeResult> newsMap = new ConcurrentHashMap<>();

        return teacherPort.getTeacherList(command.getTeachingGenre(), command.getPage(), command.getSize())
                .collectList()
                .flatMapMany(teacherResults -> {
                    List<Long> teachersIds = teacherResults.stream().map(TeacherResult::getTeacherId).toList();

                    Mono<Void> courseOpenTask = coursePort.checkRecentCourseOpenListByTeacherIds(teachersIds)
                            .flatMap(item -> {
                                courseMap.put(item.getTeacherId(), item);
                                return Flux.empty();
                            })
                            .then();

                    Mono<Void> teacherNoticeTask = boardPort.checkTeacherNewsListByTeacherIds(teachersIds)
                            .flatMap(item -> {
                                newsMap.put(item.getTeacherId(), item);
                                return Flux.empty();
                            })
                            .then();

                    return Mono.when(courseOpenTask, teacherNoticeTask)
                            .thenMany(Flux.fromIterable(teacherResults));

                })
                .flatMap(teacherResult -> Flux.just(
                        Teacher.from(
                                teacherResult,
                                Optional.ofNullable(courseMap.get(teacherResult.getTeacherId()))
                                        .isPresent(),
                                Optional.ofNullable(newsMap.get(teacherResult.getTeacherId()))
                                        .isPresent()
                        ))
                )
                .sort((before, after) -> Long.compare(after.getTeacherId(), before.getTeacherId()));
    }

    @Override
    public Mono<TeacherDetail> getTeacher(TeacherDetailSearchCommand command) {
        if (command.getIsSimple()) {
            return teacherPort.getTeacherByTeacherId(command.getTeacherId())
                    .switchIfEmpty(Mono.error(new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND)))
                    .flatMap(teacherDetailResult -> Mono.zip(
                                    coursePort.checkRecentCourseOpenListByTeacherIds(Collections.singletonList(command.getTeacherId())).collectList(),
                                    boardPort.checkTeacherNewsListByTeacherIds(Collections.singletonList(command.getTeacherId())).collectList()
                            ).map(tuples -> TeacherDetail.simple(
                                    teacherDetailResult,
                                    !tuples.getT1().isEmpty(),
                                    !tuples.getT2().isEmpty()
                            ))
                    );
        } else {
            return teacherPort.getTeacherByTeacherId(command.getTeacherId())
                    .switchIfEmpty(Mono.error(new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND)))
                    .flatMap(teacherDetailResult -> Mono.zip(
                                    coursePort.checkRecentCourseOpenListByTeacherIds(Collections.singletonList(command.getTeacherId())).collectList(),
                                    boardPort.checkTeacherNewsListByTeacherIds(Collections.singletonList(command.getTeacherId())).collectList(),
                                    courseSeriesPort.getCourseSeriesCountByTeacherId(command.getTeacherId()),
                                    coursePort.getCourseCount(),
                                    textEBookPort.getTextEBookCountResult(),
                                    boardPort.getCourseReviewCount(),
                                    boardPort.countTeacherNoticeCount()
                            )
                            .map(tuples -> TeacherDetail.from(
                                    teacherDetailResult,
                                    !tuples.getT1().isEmpty(),
                                    !tuples.getT2().isEmpty(),
                                    tuples.getT3(),
                                    tuples.getT4(),
                                    tuples.getT5(),
                                    tuples.getT6(),
                                    tuples.getT7()
                            ))
                    );
        }
    }

    @Override
    public Flux<TeacherCourseSeries> getTeacherCourseSeriesList(TeacherCourseSeriesSearchCommand command) {
        Map<Long, TeacherResult> map = new ConcurrentHashMap<>();

        return courseSeriesPort.getCourseSeriesList(command.getTeachingGenre())
                .collectList()
                .flatMapMany(courseSeriesResults -> teacherPort.getTeacherListByTeacherIds(courseSeriesResults.stream().map(CourseSeriesResult::getTeacherId).toList())
                        .flatMap(teacherResult -> {
                            map.put(teacherResult.getTeacherId(), teacherResult);
                            return Flux.just(teacherResult);
                        })
                        .flatMap(it -> Flux.fromIterable(courseSeriesResults))
                )
                .flatMap(courseSeriesResult -> Flux.just(TeacherCourseSeries.from(map.getOrDefault(courseSeriesResult.getTeacherId(), new TeacherResult()), courseSeriesResult)))
                .sort((before, after) -> Long.compare(after.getCourseSeriesId(), before.getCourseSeriesId()));
    }

    @Override
    public Flux<TeacherCourseSeries> getTeacherCourseSeriesList(SpecificTeacherCourseSeriesSearchCommand command) {
        return courseSeriesPort.getCourseSeriesListByTeacherIds(Collections.singletonList(command.getTeacherId()))
                .flatMap(courseSeriesResult -> teacherPort.getSimpleTeacherByTeacherId(courseSeriesResult.getTeacherId()).cache()
                        .flatMapMany(teacherResult -> Flux.just(TeacherCourseSeries.from(teacherResult, courseSeriesResult)))
                )
                .sort((before, after) -> Long.compare(after.getCourseSeriesId(), before.getCourseSeriesId()));
    }
}
