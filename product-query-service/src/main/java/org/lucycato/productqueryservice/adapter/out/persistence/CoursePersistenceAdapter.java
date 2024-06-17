package org.lucycato.productqueryservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productqueryservice.application.port.out.CoursePort;
import org.lucycato.productqueryservice.application.port.out.result.CheckedRecentCourseOpenResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseCountResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class CoursePersistenceAdapter implements CoursePort {

    @Override
    public Mono<CourseResult> getSimpleCourse(Long courseId) {
        return null;
    }

    @Override
    public Mono<CourseDetailResult> getCourse(Long courseId) {
        return null;
    }

    @Override
    public Flux<CheckedRecentCourseOpenResult> checkRecentCourseOpenListByTeacherIds(List<Long> teacherIds) {
        return null;
    }

    @Override
    public Flux<CourseResult> getCourseListByCourseSeriesIds(List<Long> courseSeriesIds) {
        return null;
    }

    @Override
    public Flux<CourseResult> getCourseListByTeacherIds(List<Long> teacherIds) {
        return null;
    }

    @Override
    public Mono<CourseCountResult> getCourseCount() {
        return null;
    }
}
