package org.lucycato.productqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productqueryservice.domain.*;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productqueryservice.domain.enums.TeacherNewsCategory;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class TeacherController {
    @GetMapping("open-api/product/v1/teachers")
    public Flux<Teacher> getTeachers(
            @RequestParam(name = "teachingGenre", required = false)
            TeachingGenre teachingGenre
    ) {
        return Flux.empty();
    }

    @GetMapping("open-api/product/v1/teachers/{teacherId}")
    public Mono<TeacherDetail> getTeacher(
            @PathVariable Long teacherId
    ) {
        return Mono.empty();
    }

    @GetMapping("open-api/product/v1/teachers/course-series")
    public Flux<TeacherCourseSeries> getTeacherCourseSeries(
            @RequestParam(name = "teachingGenre", required = false)
            TeachingGenre teachingGenre,
            @RequestParam(name = "isSimple", defaultValue = "true")
            Boolean isSimple,
            @RequestParam(name = "courseSeriesIds", defaultValue = "[]")
            Boolean courseSeriesIds
    ) {
        return Flux.empty();
    }

    @GetMapping("open-api/product/v1/teachers/{teacherId}/course-series")
    public Flux<TeacherCourseSeries> getTeacherCourseSeries(
            @PathVariable
            Long teacherId,
            @RequestParam(name = "isSimple", defaultValue = "true")
            Boolean isSimple
    ) {
        return Flux.empty();
    }

    @GetMapping("open-api/product/v1/teachers/news")
    public Flux<TeacherNews> getTeacherNews(
            @RequestParam(name = "teachingGenre", required = false)
            TeachingGenre teachingGenre
    ) {
        return Flux.empty();
    }

    @GetMapping("open-api/product/v1/teachers/{teacherId}/news")
    public Flux<TeacherNews> getTeacherNews(
            @PathVariable
            Long teacherId,
            @RequestParam(name = "teacherNewsCategory")
            TeacherNewsCategory teacherNewsCategory
    ) {
        return Flux.empty();
    }
}
