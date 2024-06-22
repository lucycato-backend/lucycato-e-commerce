package org.lucycato.usercoursequeryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.usercoursequeryservice.application.port.in.UserCourseUseCase;
import org.lucycato.usercoursequeryservice.application.port.in.command.UserCourseSearchCommand;
import org.lucycato.usercoursequeryservice.domain.Course;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class UserCourseController {
    private final UserCourseUseCase userCourseUseCase;

    @GetMapping("open-api/usercourse/v1/courses")
    public Flux<Course> getCourses() {
        UserCourseSearchCommand command = new UserCourseSearchCommand();
        return userCourseUseCase.getUserCurses(command);
    }
}
