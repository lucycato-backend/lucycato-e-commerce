package org.lucycato.productqueryservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productqueryservice.application.port.out.TextEBookPort;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookCountResult;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class TextEBookPersistenceAdapter implements TextEBookPort {

    @Override
    public Flux<TextEBookResult> getTextEBookListByCourseIds(List<Long> courseIds) {
        return null;
    }

    @Override
    public Flux<TextEBookResult> getTextEBookListByTeacherIds(List<Long> teacherIds) {
        return null;
    }

    @Override
    public Flux<TextEBookResult> getTextEBookListByCourseSeriesIds(List<Long> courseSeriesIds) {
        return null;
    }

    @Override
    public Mono<TextEBookCountResult> getTextEBookCountResult() {
        return null;
    }
}
