package org.lucycato.usercoursequeryservice.application.port.in.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.usercoursequeryservice.application.port.in.UserCourseUseCase;
import org.lucycato.usercoursequeryservice.application.port.in.command.UserCourseSearchCommand;
import org.lucycato.usercoursequeryservice.domain.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;


@Service
@Transactional
@RequiredArgsConstructor
public class UserCourseService implements UserCourseUseCase {
    @Override
    public Flux<Course> getUserCurses(UserCourseSearchCommand command) {
        return null;
    }
}
