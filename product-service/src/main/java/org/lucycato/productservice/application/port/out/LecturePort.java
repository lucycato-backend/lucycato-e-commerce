package org.lucycato.productservice.application.port.out;

import org.lucycato.productservice.application.port.out.result.LectureResult;
import reactor.core.publisher.Mono;

public interface LecturePort {
    Mono<LectureResult> findById(Long id);
}
