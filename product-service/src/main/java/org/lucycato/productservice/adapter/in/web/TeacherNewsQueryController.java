package org.lucycato.productservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productservice.application.port.in.TeacherNewsQueryUseCase;
import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByIdsCommand;
import org.lucycato.productservice.domain.TeacherNews;
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
public class TeacherNewsQueryController {
    private final TeacherNewsQueryUseCase teacherNewsQueryUseCase;

    //TODO: Paging
    @GetMapping("open-api/lucycato/v1/app/teacher-news/list")
    public Flux<TeacherNews.Record> getTeacherNewsList(
            @RequestParam(name = "teacher-ids", defaultValue = "[]")
            List<Long> targetTeacherIds
    ) {
        if (!targetTeacherIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetTeacherIds);
            return teacherNewsQueryUseCase.getTeacherNewsListByTargetTeacherIds(command);
        }
        return teacherNewsQueryUseCase.getTeacherNewsList();
    }

    @GetMapping("open-api/lucycato/v1/app/teacher-news/{targetTeacherNewsId}")
    public Mono<TeacherNews> getTeacherNews(
            @PathVariable
            Long targetTeacherNewsId
    ) {
        ByIdCommand command = new ByIdCommand(targetTeacherNewsId);
        return teacherNewsQueryUseCase.getTeacherNewsById(command);
    }
}
