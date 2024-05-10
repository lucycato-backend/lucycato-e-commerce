package org.lucycato.productservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productservice.application.port.in.LectureQueryUseCase;
import org.lucycato.productservice.application.port.in.LectureTextEBookQueryUseCase;
import org.lucycato.productservice.application.port.in.command.*;
import org.lucycato.productservice.domain.Lecture;
import org.lucycato.productservice.domain.LectureTextEBook;
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
public class LectureTextEBookQueryController {
    private final LectureTextEBookQueryUseCase lectureTextEBookQueryUseCase;

    @GetMapping("open-api/lucycato/v1/app/lecture-text-e-book/list")
    public Flux<LectureTextEBook.Record> getLectureTextEBookList(
            @RequestParam(name = "teacher-ids", defaultValue = "[]")
            List<Long> targetTeacherIds,
            @RequestParam(name = "lecture-ids", defaultValue = "[]")
            List<Long> targetLectureIds
    ) {
        if (!targetTeacherIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetTeacherIds);
            return lectureTextEBookQueryUseCase.getLectureTextEBookListByTargetTeacherIds(command);
        } else if (!targetLectureIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetLectureIds);
            return lectureTextEBookQueryUseCase.getLectureTextEBookListByTargetLectureIds(command);
        }
        return lectureTextEBookQueryUseCase.getLectureTextEBookList();
    }

    @GetMapping("open-api/lucycato/v1/app/lecture-text-e-book/{targetTextEBookId}")
    public Mono<LectureTextEBook> getLectureTextEBook(
            @PathVariable
            Long targetTextEBookId
    ) {
        ByIdCommand command = new ByIdCommand(targetTextEBookId);
        return lectureTextEBookQueryUseCase.getLectureTextEBookById(command);
    }

    @GetMapping("open-api/lucycato/v1/app/lecture-text-e-book/details")
    public Flux<LectureTextEBook> getLectureTextEBookDetailList(
            @RequestParam(name = "teacher-ids", defaultValue = "[]")
            List<Long> targetTeacherIds,
            @RequestParam(name = "lecture-ids", defaultValue = "[]")
            List<Long> targetLectureIds
    ) {
        if (!targetTeacherIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetTeacherIds);
            return lectureTextEBookQueryUseCase.getLectureTextEBookDetailListByTargetTeacherIds(command);
        } else if (!targetLectureIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetLectureIds);
            return lectureTextEBookQueryUseCase.getLectureTextEBookDetailListByTargetLectureIds(command);
        }
        return lectureTextEBookQueryUseCase.getLectureTextEBookDetailList();
    }
}
