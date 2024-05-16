package org.lucycato.productservice.adapter.out.persistence.vo;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productservice.adapter.out.persistence.repository.LectureReactiveRepository;
import org.lucycato.productservice.application.port.out.LecturePort;
import org.lucycato.productservice.application.port.out.result.LectureResult;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class LectureAdapter implements LecturePort {
    private final LectureReactiveRepository lectureReactiveRepository;
    @Override
    public Mono<LectureResult> findById(Long id) {
        return lectureReactiveRepository.findById(id).map(LectureResult::from);
    }
}
