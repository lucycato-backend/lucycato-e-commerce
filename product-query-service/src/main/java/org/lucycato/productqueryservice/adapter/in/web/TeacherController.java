package org.lucycato.productqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.Paging;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productqueryservice.application.port.in.TeacherUseCase;
import org.lucycato.productqueryservice.application.port.in.command.*;
import org.lucycato.productqueryservice.domain.Teacher;
import org.lucycato.productqueryservice.domain.TeacherCourseSeries;
import org.lucycato.productqueryservice.domain.TeacherDetail;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherUseCase teacherUseCase;

    @GetMapping("open-api/v1/teachers")
    public Mono<Paging<List<Teacher>>> getTeacherList(
            @RequestParam(name = "teachingGenre", required = false)
            TeachingGenre teachingGenre,
            @RequestParam(name = "page", defaultValue = "0")
            Integer page,
            @RequestParam(name = "size", defaultValue = "10")
            Integer size

    ) {
        TeacherSearchCommand command = new TeacherSearchCommand(
                teachingGenre,
                page,
                size
        );
        return teacherUseCase.getTeacherList(command)
                .collectList()
                .map(teachers ->
                    Paging.CREATE(teachers, page, size, teachers.size() < size)
                );
    }

    @GetMapping("open-api/v1/teachers/{teacherId}")
    public Mono<TeacherDetail> getTeacher(
            @PathVariable
            Long teacherId,
            @RequestParam(name = "isSimple", required = false)
            Boolean isSimple
    ) {
        TeacherDetailSearchCommand command = new TeacherDetailSearchCommand(
                teacherId,
                isSimple
        );
        return teacherUseCase.getTeacher(command);
    }

    @GetMapping("open-api/v1/teachers/course-series")
    public Flux<TeacherCourseSeries> getTeacherCourseSeriesList(
            @RequestParam(name = "teachingGenre", required = false)
            TeachingGenre teachingGenre
    ) {
        TeacherCourseSeriesSearchCommand command = new TeacherCourseSeriesSearchCommand(
                teachingGenre
        );
        return teacherUseCase.getTeacherCourseSeriesList(command);
    }

    @GetMapping("open-api/v1/teachers/{teacherId}/course-series")
    public Flux<TeacherCourseSeries> getTeacherCourseSeriesList(
            @PathVariable
            Long teacherId
    ) {
        SpecificTeacherCourseSeriesSearchCommand command = new SpecificTeacherCourseSeriesSearchCommand(
                teacherId
        );
        return teacherUseCase.getTeacherCourseSeriesList(command);
    }
}
