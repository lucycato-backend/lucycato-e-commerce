package org.lucycato.productservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productservice.application.port.in.TeacherQueryUseCase;
import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByTeachingGenreCommand;
import org.lucycato.productservice.domain.Teacher;
import org.lucycato.productservice.domain.enums.TeachingGenre;
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
public class TeacherQueryController {
    private final TeacherQueryUseCase teacherQueryUseCase;

    @GetMapping("open-api/lucycato/v1/app/teacher/list")
    public Flux<Teacher.Record> getTeacherList(
            @RequestParam(name = "teacher-genres", defaultValue = "[]")
            List<TeachingGenre> targetTeachingGenres
    ) {
        if (!targetTeachingGenres.isEmpty()) {
            ByTeachingGenreCommand command = new ByTeachingGenreCommand(targetTeachingGenres);
            return teacherQueryUseCase.getTeacherListByTargetTeacherIds(command);
        }
        return teacherQueryUseCase.getTeacherList();
    }

    @GetMapping("open-api/lucycato/v1/app/teacher/{targetTeacherId}")
    public Mono<Teacher> getTeacherById(
            @PathVariable
            Long targetTeacherId
    ) {
        ByIdCommand command = new ByIdCommand(targetTeacherId);
        return teacherQueryUseCase.getTeacherById(command);
    }
}
