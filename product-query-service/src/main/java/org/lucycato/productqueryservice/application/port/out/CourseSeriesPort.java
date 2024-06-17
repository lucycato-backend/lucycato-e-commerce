package org.lucycato.productqueryservice.application.port.out;

import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CourseSeriesPort {

    Flux<CourseSeriesResult> getCourseSeriesListByTeacherIds(List<Long> teacherIds);

    Mono<CourseSeriesDetailResult> getCourseSeries(Long courseSeriesId);

    Mono<CourseSeriesResult> getSimpleCourseSeries(Long courseSeriesId);

    Mono<Long> getCourseSeriesCount();
}
