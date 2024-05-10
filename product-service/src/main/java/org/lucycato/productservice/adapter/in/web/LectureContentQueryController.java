package org.lucycato.productservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productservice.application.port.in.LectureContentQueryUseCase;
import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByIdsCommand;
import org.lucycato.productservice.domain.LectureContent;
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
public class LectureContentQueryController {
    private final LectureContentQueryUseCase lectureContentQueryUseCase;

    @GetMapping("open-api/lucycato/v1/app/lecture-content/list")
    public Flux<LectureContent.Record> getLectureContent(
            @RequestParam(name = "lecture-ids", defaultValue = "[]")
            List<Long> targetLectureIds
    ) {
        if (!targetLectureIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetLectureIds);
            return lectureContentQueryUseCase.getLectureContentByLectureIds(command);
        }
        return lectureContentQueryUseCase.getLectureContentList();
    }

    @GetMapping("open-api/lucycato/v1/app/lecture-content/{targetLectureContentId}")
    public Mono<LectureContent> getLectureContent(
            @PathVariable
            Long targetLectureId
    ) {
        ByIdCommand command = new ByIdCommand(targetLectureId);
        return lectureContentQueryUseCase.getLectureContentById(command);
    }
}