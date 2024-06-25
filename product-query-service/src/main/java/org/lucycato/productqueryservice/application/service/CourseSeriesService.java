package org.lucycato.productqueryservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productqueryservice.application.port.in.CourseSeriesUseCase;
import org.lucycato.productqueryservice.application.port.in.command.CourseSeriesDetailSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.SpecificCourseSeriesCourseSearchByTeacherIdCommand;
import org.lucycato.productqueryservice.application.port.in.command.SpecificCourseSeriesCourseSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.SpecificCourseSeriesTextEBookSearchCommand;
import org.lucycato.productqueryservice.application.port.out.CoursePort;
import org.lucycato.productqueryservice.application.port.out.CourseSeriesPort;
import org.lucycato.productqueryservice.application.port.out.TeacherPort;
import org.lucycato.productqueryservice.application.port.out.TextEBookPort;
import org.lucycato.productqueryservice.application.port.out.result.*;
import org.lucycato.productqueryservice.domain.CourseSeriesCourse;
import org.lucycato.productqueryservice.domain.CourseSeriesDetail;
import org.lucycato.productqueryservice.domain.CourseSeriesTextEBook;
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
public class CourseSeriesService implements CourseSeriesUseCase {
    private final CourseSeriesPort courseSeriesPort;

    private final TeacherPort teacherPort;

    private final CoursePort coursePort;

    private final TextEBookPort textEBookPort;

    @Override
    public Mono<CourseSeriesDetail> getCourseSeries(CourseSeriesDetailSearchCommand command) {
        return courseSeriesPort.getCourseSeriesByCourseSeriesId(command.getCourseSeriesId())
                .switchIfEmpty(Mono.error(new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND)))
                .map(CourseSeriesDetail::from);
    }

    //TODO: 성능 최적화
    @Override
    public Flux<CourseSeriesCourse> getCourseSeriesCourseList(SpecificCourseSeriesCourseSearchCommand command) {
        Map<Long, TextEBookResult> textEBookMap = new ConcurrentHashMap<>();
        Map<Long, CheckedRecentCourseOpenResult> checkRecentOpenCourseMap = new ConcurrentHashMap<>();

        return coursePort.getCourseListByCourseSeriesIds(Collections.singletonList(command.getCourseSeriesId()))
                .collectList()
                .flatMapMany(courseResults -> {
                    List<Long> courseIds = courseResults.stream().map(CourseResult::getCourseId).toList();

                    Mono<Void> textEBookTask = textEBookPort.getTextEBookListByCourseIds(courseIds)
                            .flatMap(item -> {
                                textEBookMap.put(item.getCourseId(), item);
                                return Flux.empty();
                            })
                            .then();

                    Mono<Void> checkRecentCourseOpenTask = coursePort.checkRecentCourseOpenListByCourseIds(courseIds)
                            .flatMap(item -> {
                                checkRecentOpenCourseMap.put(item.getCourseId(), item);
                                return Flux.empty();
                            })
                            .then();

                    return Mono.when(textEBookTask, checkRecentCourseOpenTask)
                            .thenMany(Flux.fromIterable(courseResults));

                })
                .flatMap(courseResult -> Mono.zip(
                                        teacherPort.getSimpleTeacherByCourseSeriesId(courseResult.getCourseSeriesId()),
                                        courseSeriesPort.getSimpleCourseSeriesByCourseSeriesId(courseResult.getCourseSeriesId())
                                )
                                .cache()
                                .flatMapMany(tuples -> Flux.just(CourseSeriesCourse.from(
                                        courseResult,
                                        tuples.getT1(),
                                        tuples.getT2(),
                                        textEBookMap.getOrDefault(courseResult.getCourseId(), new TextEBookResult()),
                                        Optional.ofNullable(checkRecentOpenCourseMap.get(courseResult.getCourseId()))
                                                .isPresent()
                                )))
                )
                .sort((before, after) -> Long.compare(after.getCourseId(), before.getCourseId()));


//        return Mono.when(process)
//                .flatMapMany(it -> coursePort.getCourseListByCourseSeriesId(command.getCourseSeriesId()))
//                .flatMap(courseResult -> Mono.zip(
//                                        teacherPort.getSimpleTeacher(command.getCourseSeriesId()),
//                                        courseSeriesPort.getSimpleCourseSeries(courseResult.getCourseId())
//                                ).cache()
//                                .flatMapMany(tuples -> Flux.just(CourseSeriesCourse.from(
//                                        courseResult,
//                                        tuples.getT1(),
//                                        tuples.getT2(),
//                                        map.get(courseResult.getCourseId())))
//                                )
//                );
    }

    //TODO: 성능 최적화
    @Override
    public Flux<CourseSeriesCourse> getCourseSeriesCourseListByTeacherId(SpecificCourseSeriesCourseSearchByTeacherIdCommand command) {
        Map<Long, CourseSeriesResult> courseSeriesMap = new ConcurrentHashMap<>();
        Map<Long, TextEBookResult> textEBookMap = new ConcurrentHashMap<>();
        Map<Long, CheckedRecentCourseOpenResult> checkRecentOpenCourseMap = new ConcurrentHashMap<>();

        return coursePort.getCourseListByTeacherIds(Collections.singletonList(command.getTeacherId()))
                .collectList()
                .flatMapMany(courseResults -> {
                    List<Long> courseIds = courseResults.stream().map(CourseResult::getCourseId).toList();
                    Mono<Void> courseTask = courseSeriesPort.getCourseSeriesListByCourseIds(courseIds)
                            .flatMap(item -> {
                                courseSeriesMap.put(item.getCourseSeriesId(), item);
                                return Flux.empty();
                            })
                            .then();

                    Mono<Void> textEBookTask = textEBookPort.getTextEBookListByCourseIds(courseIds)
                            .flatMap(item -> {
                                textEBookMap.put(item.getCourseId(), item);
                                return Flux.empty();
                            })
                            .then();

                    Mono<Void> checkRecentCourseOpenTask = coursePort.checkRecentCourseOpenListByCourseIds(courseIds)
                            .flatMap(item -> {
                                checkRecentOpenCourseMap.put(item.getCourseId(), item);
                                return Flux.empty();
                            })
                            .then();

                    return Mono.when(courseTask, textEBookTask, checkRecentCourseOpenTask)
                            .thenMany(Flux.fromIterable(courseResults));

                })
                .flatMap(courseResult -> teacherPort.getSimpleTeacherByTeacherId(command.getTeacherId())
                        .defaultIfEmpty(new TeacherResult())
                        .cache()
                        .flatMapMany(teacherResult -> Flux.just(CourseSeriesCourse.from(
                                courseResult,
                                teacherResult,
                                courseSeriesMap.getOrDefault(courseResult.getCourseSeriesId(), new CourseSeriesResult()),
                                textEBookMap.getOrDefault(courseResult.getCourseId(), new TextEBookResult()),
                                Optional.ofNullable(checkRecentOpenCourseMap.get(courseResult.getCourseId()))
                                        .isPresent()
                        )))
                )
                .sort((before, after) -> Long.compare(after.getCourseId(), before.getCourseId()));
    }

    @Override
    public Flux<CourseSeriesTextEBook> getCourseSeriesTextEBookList(SpecificCourseSeriesTextEBookSearchCommand command) {
        Map<Long, CourseSeriesResult> map = new ConcurrentHashMap<>();
        textEBookPort.getTextEBookListByTeacherIds(Collections.singletonList(command.getTeacherId()))
                .doOnNext(it -> System.out.println("HELLO ::: " + it.getTextEBookId()))
                .subscribe();

        return textEBookPort.getTextEBookListByTeacherIds(Collections.singletonList(command.getTeacherId()))
                .collectList()
                .flatMapMany(textEBookResults -> courseSeriesPort.getCourseSeriesListByTextEBookIds(textEBookResults.stream().map(TextEBookResult::getTextEBookId).toList())
                        .flatMap(courseSeriesResult ->  {
                            map.put(courseSeriesResult.getCourseSeriesId(), courseSeriesResult);
                            return Flux.just(courseSeriesResult);
                        })
                        .flatMap(it -> Flux.fromIterable(textEBookResults))
                )
                .doOnNext(it -> System.out.println("HELLO2 ::: " + it.getTextEBookId()))
                .flatMap(textEBookResult -> Flux.just(CourseSeriesTextEBook.from(map.getOrDefault(textEBookResult.getCourseSeriesId(), new CourseSeriesResult()), textEBookResult)))
                .sort((before, after) -> Long.compare(after.getTextEBookId(), before.getTextEBookId()));
    }
}
