package org.lucycato.productqueryservice.application.port.out;

import org.lucycato.productqueryservice.application.port.out.result.TextEBookCountResult;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TextEBookPort {

    Flux<TextEBookResult> getTextEBookListByCourseIds(List<Long> courseIds);

    Flux<TextEBookResult> getTextEBookListByTeacherIds(List<Long> teacherIds);

    Flux<TextEBookResult> getTextEBookListByCourseSeriesIds(List<Long> courseSeriesIds);

    Mono<TextEBookCountResult> getTextEBookCountResult();
}
