package org.lucycato.productservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productservice.application.port.in.LectureSeriesQueryUseCase;
import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByIdsCommand;
import org.lucycato.productservice.domain.LectureSeries;
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
public class LectureSeriesQueryController {
    private final LectureSeriesQueryUseCase lectureSeriesQueryUseCase;

    @GetMapping("open-api/lucycato/v1/app/lecture-series/list")
    public Flux<LectureSeries.Record> getLectureSeriesList(
            @RequestParam(name = "teacher-ids", defaultValue = "[]")
            List<Long> targetTeacherIds
    ) {
        if (!targetTeacherIds.isEmpty()) {
            ByIdsCommand command = new ByIdsCommand(targetTeacherIds);
            return lectureSeriesQueryUseCase.getLectureSeriesListByTargetTeacherIds(command);
        }
        return lectureSeriesQueryUseCase.getLectureSeriesList();
    }

    @GetMapping("open-api/lucycato/v1/app/lecture-series/{targetLectureSeriesId}")
    public Mono<LectureSeries> getLectureSeries(
            @PathVariable
            Long targetLectureSeriesId
    ) {
        ByIdCommand command = new ByIdCommand(targetLectureSeriesId);
        return lectureSeriesQueryUseCase.getLectureSeriesById(command);
    }
}
