package org.lucycato.productservice.application.port.in;

import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByIdsCommand;
import org.lucycato.productservice.domain.LectureContent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LectureContentQueryUseCase {
    Flux<LectureContent.Record> getLectureContentList();

    Flux<LectureContent.Record> getLectureContentByLectureIds(ByIdsCommand command);

    Mono<LectureContent> getLectureContentById(ByIdCommand command);
}
