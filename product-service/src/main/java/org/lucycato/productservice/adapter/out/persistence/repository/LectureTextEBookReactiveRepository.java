package org.lucycato.productservice.adapter.out.persistence.repository;

import org.lucycato.productservice.adapter.out.persistence.entity.LectureTextEBookR2dbcEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LectureTextEBookReactiveRepository extends ReactiveCrudRepository<LectureTextEBookR2dbcEntity, Long> {
}
