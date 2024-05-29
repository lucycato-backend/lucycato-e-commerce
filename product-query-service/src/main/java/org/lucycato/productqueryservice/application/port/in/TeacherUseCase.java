package org.lucycato.productqueryservice.application.port.in;


import org.lucycato.productqueryservice.application.port.in.command.TeacherSearchCommand;
import org.lucycato.productqueryservice.domain.Teacher;
import reactor.core.publisher.Flux;

import java.util.List;

public interface TeacherUseCase {
    Flux<Teacher> getTeachers(TeacherSearchCommand command);
}
