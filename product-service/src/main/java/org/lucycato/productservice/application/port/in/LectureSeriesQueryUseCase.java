package org.lucycato.productservice.application.port.in;

import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByIdsCommand;
import org.lucycato.productservice.domain.LectureSeries;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LectureSeriesQueryUseCase {
    Flux<LectureSeries.Record> getLectureSeriesList();

    Flux<LectureSeries.Record> getLectureSeriesListByTargetTeacherIds(ByIdsCommand command);

    Mono<LectureSeries> getLectureSeriesById(ByIdCommand command);
}
