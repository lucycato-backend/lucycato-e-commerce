package org.lucycato.usercoursequeryservice.application.port.in;

import org.lucycato.usercoursequeryservice.application.port.in.command.UserCourseSearchCommand;
import org.lucycato.usercoursequeryservice.domain.Course;
import reactor.core.publisher.Flux;

public interface UserCourseUseCase {
    Flux<Course> getUserCurses(UserCourseSearchCommand command);
}
