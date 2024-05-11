package org.lucycato.productservice.application.port.in;

import org.lucycato.productservice.application.port.in.command.*;
import org.lucycato.productservice.domain.Lecture;
import org.lucycato.productservice.domain.LectureSeries;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LectureQueryUseCase {
    Flux<Lecture.Record> getLectureList();

    Flux<Lecture.Record> getLectureListByTargetTeacherIds(ByIdsCommand command);

    Flux<Lecture.Record> getLectureListByTargetLectureSeriesIds(ByIdsCommand command);

    Mono<Lecture> getLectureById(ByIdCommand command);
}
