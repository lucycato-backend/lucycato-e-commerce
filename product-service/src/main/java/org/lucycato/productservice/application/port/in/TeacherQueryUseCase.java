package org.lucycato.productservice.application.port.in;

import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByTeachingGenreCommand;
import org.lucycato.productservice.domain.Teacher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherQueryUseCase {
    Flux<Teacher.Record> getTeacherList();

    Flux<Teacher.Record> getTeacherListByTargetTeacherIds(ByTeachingGenreCommand command);

    Mono<Teacher> getTeacherById(ByIdCommand command);
}
