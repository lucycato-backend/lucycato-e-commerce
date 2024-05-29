package org.lucycato.productqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productqueryservice.application.port.in.TeacherUseCase;
import org.lucycato.productqueryservice.application.port.in.command.TeacherDetailSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.TeacherSearchCommand;
import org.lucycato.productqueryservice.domain.Teacher;
import org.lucycato.productqueryservice.domain.TeacherCourseSeries;
import org.lucycato.productqueryservice.domain.TeacherDetail;
import org.lucycato.productqueryservice.domain.TeacherNews;
import org.lucycato.productqueryservice.domain.enums.TeacherNewsCategory;
import org.lucycato.productqueryservice.domain.enums.TeacherStatus;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherUseCase teacherUseCase;

    @GetMapping("open-api/product/v1/teachers")
    public Flux<Teacher> getTeachers(
            @RequestParam(name = "genre", required = false)
            TeachingGenre genre,
            @RequestParam(name = "status", defaultValue = "REGISTERED")
            TeacherStatus status
    ) {
        TeacherSearchCommand command = new TeacherSearchCommand(
                genre,
                status
        );
        return teacherUseCase.getTeachers(command);
    }

    @GetMapping("open-api/product/v1/teachers/{teacherId}")
    public Mono<TeacherDetail> getTeacher(
            @PathVariable Long teacherId
    ) {
        TeacherDetailSearchCommand command = new TeacherDetailSearchCommand(
                teacherId
        );
        return teacherUseCase.getTeacher(command);
    }

    @GetMapping("open-api/product/v1/teachers/course-series")
    public Flux<TeacherCourseSeries> getTeacherCourseSeries(
            @RequestParam(name = "teachingGenre", required = false)
            TeachingGenre teachingGenre,
            @RequestParam(name = "isSimple", defaultValue = "true")
            Boolean isSimple
    ) {
        TeacherCourseSeriesSearchCommand command = new TeacherCourseSeriesSearchCommand(
                teachingGenre,
                isSimple
        );
        return teacherUseCase.getTeacherCourseSeries(command);
    }

    @GetMapping("open-api/product/v1/teachers/{teacherId}/course-series")
    public Flux<TeacherCourseSeries> getTeacherCourseSeries(
            @PathVariable
            Long teacherId,
            @RequestParam(name = "isSimple", defaultValue = "true")
            Boolean isSimple
    ) {
        SpecificTeacherCourseSeriesSearchCommand command = new SpecificTeacherCourseSeriesSearchCommand(
                teacherId,
                isSimple
        );
        return teacherUseCase.getTeacherCourseSeries(command);
    }

    @GetMapping("open-api/product/v1/teachers/news")
    public Flux<TeacherNews> getTeacherNews(
            @RequestParam(name = "teachingGenre", required = false)
            TeachingGenre teachingGenre,
            @RequestParam(name = "teacherNewsCategory", required = false)
            TeacherNewsCategory newsCategory
    ) {
        TeacherNewsSearchCommand command = new TeacherNewsSearchCommand(
                teachingGenre,
                newsCategory
        );
        return teacherUseCase.getTeacherNews(command);
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
