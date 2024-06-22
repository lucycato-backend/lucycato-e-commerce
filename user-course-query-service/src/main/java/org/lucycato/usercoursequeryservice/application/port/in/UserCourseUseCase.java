package org.lucycato.usercoursequeryservice.application.port.in;

import org.lucycato.usercoursequeryservice.application.port.in.command.UserCourseDetailSearchCommand;
import org.lucycato.usercoursequeryservice.application.port.in.command.UserCourseSearchCommand;
import org.lucycato.usercoursequeryservice.domain.Course;
import org.lucycato.usercoursequeryservice.domain.CourseDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserCourseUseCase {
    Flux<Course> getCurses(UserCourseSearchCommand command);

    Mono<CourseDetail> getCures(UserCourseDetailSearchCommand command);
}
