package org.lucycato.productqueryservice.application.port.out;

import org.lucycato.productqueryservice.application.port.out.result.CheckedRecentCourseOpenResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseCountResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CoursePort {

    Mono<CourseResult> getSimpleCourse(Long courseId);

    Mono<CourseDetailResult> getCourse(Long courseId);

    Flux<Long> getCourseIdsByCourseSeriesId(Long courseSeriesId);

    Flux<CheckedRecentCourseOpenResult> checkRecentCourseOpenListByTeacherIds(List<Long> teacherIds);

    Flux<CheckedRecentCourseOpenResult> checkRecentCourseOpenListByCourseIds(List<Long> courseIds);

    Flux<CourseResult> getCourseListByCourseSeriesIds(List<Long> courseSeriesIds);

    Flux<CourseResult> getCourseListByTeacherIds(List<Long> teacherIds);

    Mono<CourseCountResult> getCourseCount();
}
