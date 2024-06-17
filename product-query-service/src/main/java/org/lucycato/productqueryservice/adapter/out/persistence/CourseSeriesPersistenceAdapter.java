package org.lucycato.productqueryservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productqueryservice.application.port.out.CourseSeriesPort;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class CourseSeriesPersistenceAdapter implements CourseSeriesPort {

    @Override
    public Flux<CourseSeriesResult> getCourseSeriesListByTeacherIds(List<Long> teacherIds) {
        return null;
    }

    @Override
    public Mono<CourseSeriesDetailResult> getCourseSeries(Long courseSeriesId) {
        return null;
    }

    @Override
    public Mono<CourseSeriesResult> getSimpleCourseSeries(Long courseSeriesId) {
        return null;
    }

    @Override
    public Mono<Integer> getCourseSeriesCount() {
        return null;
    }
}
