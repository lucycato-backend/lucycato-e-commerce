package org.lucycato.productservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productservice.application.port.in.LectureQueryUseCase;
import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByIdsCommand;
import org.lucycato.productservice.domain.Lecture;
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
public class LectureQueryController {
    private final LectureQueryUseCase lectureQueryUseCase;

    @GetMapping("open-api/lucycato/v1/app/lecture/list")
    public Flux<Lecture.Record> getLectureList(
            @RequestParam(name = "teacher-ids", defaultValue = "[]")
            List<Long> targetTeacherIds,
            @RequestParam(name = "lecture-series-ids", defaultValue = "[]")
            List<Long> targetLectureSeriesIds
    ) {
        if (!targetTeacherIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetTeacherIds);
            return lectureQueryUseCase.getLectureListByTargetTeacherIds(command);
        } else if (!targetLectureSeriesIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetLectureSeriesIds);
            return lectureQueryUseCase.getLectureListByTargetLectureSeriesIds(command);
        }
        return lectureQueryUseCase.getLectureList();
    }

    @GetMapping("open-api/lucycato/v1/app/lecture/{targetLectureId}")
    public Mono<Lecture> getLecture(
            @PathVariable
            Long targetLectureId
    ) {
        ByIdCommand command = new ByIdCommand(targetLectureId);
        return lectureQueryUseCase.getLectureById(command);
    }
}
