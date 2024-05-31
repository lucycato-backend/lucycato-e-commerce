package org.lucycato.productservice.adapter.out.persistence.repository;

import org.lucycato.productservice.adapter.out.persistence.entity.TeacherNewsR2dbcEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TeacherNewsReactiveRepository extends ReactiveCrudRepository<TeacherNewsR2dbcEntity, Long> {
}
