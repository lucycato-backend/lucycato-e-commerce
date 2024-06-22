package org.lucycato.usercoursequeryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.usercoursequeryservice.application.port.in.UserCourseUseCase;
import org.lucycato.usercoursequeryservice.application.port.in.command.UserCourseDetailSearchCommand;
import org.lucycato.usercoursequeryservice.application.port.in.command.UserCourseSearchCommand;
import org.lucycato.usercoursequeryservice.domain.Course;
import org.lucycato.usercoursequeryservice.domain.CourseDetail;
import org.lucycato.usercoursequeryservice.domain.enums.CourseGenre;
import org.lucycato.usercoursequeryservice.domain.enums.SubjectCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class UserCourseController {
    private final UserCourseUseCase userCourseUseCase;

    @GetMapping("open-api/usercourse/v1/courses")
    public Flux<Course> getCourses(
            @RequestParam(name = "courseGenre", required = false)
            CourseGenre courseGenre,
            @RequestParam(name = "subjectCategory", required = false)
            SubjectCategory subjectCategory
    ) {
        UserCourseSearchCommand command = new UserCourseSearchCommand(
                courseGenre,
                subjectCategory
        );
        return userCourseUseCase.getCurses(command);
    }

    @GetMapping("open-api/usercourse/v1/courses/{courseId}")
    public Mono<CourseDetail> getCourse(
            @PathVariable
            Long courseId
    ) {
        UserCourseDetailSearchCommand command = new UserCourseDetailSearchCommand(
                courseId
        );
        return userCourseUseCase.getCures(command);
    }
}
