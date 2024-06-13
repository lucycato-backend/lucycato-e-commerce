package org.lucycato.productservice.adapter.out.persistence.repository;

import org.lucycato.productservice.adapter.out.persistence.entity.LectureContentR2dbcEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LectureContentReactiveRepository extends ReactiveCrudRepository<LectureContentR2dbcEntity, Long> {
}

