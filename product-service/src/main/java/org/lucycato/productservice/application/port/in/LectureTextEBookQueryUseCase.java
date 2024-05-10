package org.lucycato.productservice.application.port.in;

import org.lucycato.productservice.application.port.in.command.*;
import org.lucycato.productservice.domain.LectureTextEBook;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LectureTextEBookQueryUseCase {
    Flux<LectureTextEBook> getLectureTextEBookDetailList();

    Flux<LectureTextEBook> getLectureTextEBookDetailListByTargetTeacherIds(ByIdsCommand command);

    Flux<LectureTextEBook> getLectureTextEBookDetailListByTargetLectureIds(ByIdsCommand command);

    Flux<LectureTextEBook.Record> getLectureTextEBookList();

    Flux<LectureTextEBook.Record> getLectureTextEBookListByTargetTeacherIds(ByIdsCommand command);

    Flux<LectureTextEBook.Record> getLectureTextEBookListByTargetLectureIds(ByIdsCommand command);

    Mono<LectureTextEBook> getLectureTextEBookById(ByIdCommand command);
}
