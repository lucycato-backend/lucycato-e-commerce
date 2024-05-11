package org.lucycato.productservice.application.port.in;

import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByIdsCommand;
import org.lucycato.productservice.domain.LectureReview;
import org.lucycato.productservice.domain.LectureSeries;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LectureReviewQueryUseCase {
    Flux<LectureReview.Record> getLectureReviewList();

    Flux<LectureReview.Record> getLectureReviewListByTargetTeacherIds(ByIdsCommand command);

    Flux<LectureReview.Record> getLectureReviewListByTargetLectureIds(ByIdsCommand command);

    Mono<LectureReview> getLectureReviewById(ByIdCommand command);
}
