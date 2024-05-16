package org.lucycato.productservice.adapter.out.persistence.repository;

import org.lucycato.productservice.adapter.out.persistence.entity.LectureR2dbcEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LectureReactiveRepository extends ReactiveCrudRepository<LectureR2dbcEntity, Long> {
}
