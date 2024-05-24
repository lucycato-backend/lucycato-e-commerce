package org.lucycato.productqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productqueryservice.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CourseController {

    @GetMapping("open-api/product/v1/courses")
    public Flux<Course> getCourses() {
        return Flux.empty();
    }

    @GetMapping("open-api/product/v1/courses/{courseId}")
    public Mono<CourseDetail> getCourse() {
        return Mono.empty();
    }

    @GetMapping("open-api/product/v1/courses/{courseId}/lectures")
    public Flux<CourseLecture> getCourseLectures(
            @PathVariable
            Long courseId
    ) {
        return Flux.empty();
    }

    @GetMapping("open-api/product/v1/courses/{courseId}/text-e-books")
    public Flux<CourseTextEBook> getCourseTextEBooks(
            @PathVariable
            Long courseId
    ) {
        return Flux.empty();
    }

    @GetMapping("open-api/product/v1/courses/by-teacher/{teacherId}/reviews")
    public Flux<CourseReview> getCourseReviewsByTeacher(
            @PathVariable
            Long teacherId
    ) {
        return Flux.empty();
    }

    @GetMapping("open-api/product/v1/courses/{courseId}/reviews")
    public Flux<CourseReview> getCourseReviews(
            @PathVariable
            Long courseId
    ) {
        return Flux.empty();
    }
}
