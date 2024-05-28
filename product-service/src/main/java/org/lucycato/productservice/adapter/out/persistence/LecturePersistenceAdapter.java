package org.lucycato.productservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productservice.adapter.out.persistence.repository.LectureReactiveRepository;
import org.lucycato.productservice.application.port.out.LecturePort;
import org.lucycato.productservice.application.port.out.result.LectureResult;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements LecturePort {
    private final LectureReactiveRepository lectureReactiveRepository;
    @Override
    public Mono<LectureResult> getLectureById(Long id) {
        return lectureReactiveRepository.findById(id).onErrorResume(Exception.class, Mono::error).map(LectureResult::from);
    }
}
