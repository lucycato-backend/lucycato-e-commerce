package org.lucycato.productservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productservice.application.port.in.LectureReviewQueryUseCase;
import org.lucycato.productservice.application.port.in.TeacherNewsQueryUseCase;
import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByIdsCommand;
import org.lucycato.productservice.domain.LectureReview;
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
public class LectureReviewQueryController {
    private final LectureReviewQueryUseCase lectureReviewQueryUseCase;

    //TODO: Paging
    @GetMapping("open-api/lucycato/v1/app/lecture-reviews/list")
    public Flux<LectureReview.Record> getLectureReviewList(
            @RequestParam(name = "teacher-ids", defaultValue = "[]")
            List<Long> targetTeacherIds,
            @RequestParam(name = "lecture-ids", defaultValue = "[]")
            List<Long> targetLectureIds
    ) {
        if (!targetTeacherIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetTeacherIds);
            return lectureReviewQueryUseCase.getLectureReviewListByTargetTeacherIds(command);
        } else if (!targetLectureIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetLectureIds);
            return lectureReviewQueryUseCase.getLectureReviewListByTargetLectureIds(command);
        }
        return lectureReviewQueryUseCase.getLectureReviewList();
    }

    @GetMapping("open-api/lucycato/v1/app/lecture-reviews/{targetLectureReviewId}")
    public Mono<LectureReview> getLectureReview(
            @PathVariable
            Long targetLectureReviewId
    ) {
        ByIdCommand command = new ByIdCommand(targetLectureReviewId);
        return lectureReviewQueryUseCase.getLectureReviewById(command);
    }
}
