package org.lucycato.productqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productqueryservice.application.port.in.CourseSeriesUseCase;
import org.lucycato.productqueryservice.application.port.in.command.CourseSeriesDetailSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.SpecificCourseSeriesTextEBookSearchCommand;
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
    private final CourseSeriesUseCase courseSeriesUseCase;

    @GetMapping("open-api/v1/course-series/{courseSeriesId}")
    public Mono<CourseSeriesDetail> getCourseSeries(
            @PathVariable
            Long courseSeriesId
    ) {
        CourseSeriesDetailSearchCommand command = new CourseSeriesDetailSearchCommand(courseSeriesId);
        return courseSeriesUseCase.getCourseSeriesDetail(command);
    }

    @GetMapping("open-api/v1/course-series/by-teacher/{teacherId}/text-e-books")
    public Flux<CourseSeriesTextEBook> getCourseSeriesTextEBooksByTeacherId(
            @PathVariable
            Long teacherId
    ) {
        SpecificCourseSeriesTextEBookSearchCommand command = new SpecificCourseSeriesTextEBookSearchCommand(teacherId);
        return courseSeriesUseCase.getCourseSeriesTextEBooks(command);
    }
}
