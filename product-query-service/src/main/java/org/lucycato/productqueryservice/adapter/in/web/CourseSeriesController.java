package org.lucycato.productqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productqueryservice.domain.CourseSeries;
import org.lucycato.productqueryservice.domain.CourseSeriesDetail;
import org.lucycato.productqueryservice.domain.CourseSeriesTextEBook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CourseSeriesController {

    @GetMapping("open-api/product/v1/course-series")
    public Flux<CourseSeries> getCourseSeries(
    ) {
        return Flux.empty();
    }

    @GetMapping("open-api/product/v1/course-series/{courseSeriesId}")
    public Mono<CourseSeriesDetail> getCourseSeries(
            @PathVariable
            Long courseSeriesId
    ) {
        return Mono.empty();
    }

    @GetMapping("open-api/product/v1/course-series/by-teacher/{teacherId}/text-e-books")
    public Flux<CourseSeriesTextEBook> getCourseTextEBooksByTeacherId(
            @PathVariable
            Long teacherId
    ) {
        return Flux.empty();
    }
}
