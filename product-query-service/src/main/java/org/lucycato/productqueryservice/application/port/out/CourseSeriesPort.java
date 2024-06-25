package org.lucycato.productqueryservice.application.port.out;

import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesResult;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CourseSeriesPort {

    Flux<CourseSeriesResult> getCourseSeriesListByTeacherIds(List<Long> teacherIds);

    Flux<CourseSeriesResult> getCourseSeriesListByCourseIds(List<Long> courseIds);

    Flux<CourseSeriesResult> getCourseSeriesListByTextEBookIds(List<Long> textEBookIds);

    Mono<CourseSeriesDetailResult> getCourseSeriesByCourseSeriesId(Long courseSeriesId);

    Flux<CourseSeriesResult> getCourseSeriesList(TeachingGenre teachingGenre);

    Mono<CourseSeriesResult> getSimpleCourseSeriesByCourseSeriesId(Long courseSeriesId);

    Mono<Long> getCourseSeriesCountByTeacherId(Long teacherId);
}
