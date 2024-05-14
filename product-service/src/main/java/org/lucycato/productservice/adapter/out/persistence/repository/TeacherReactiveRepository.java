package org.lucycato.productservice.adapter.out.persistence.repository;

import org.lucycato.productservice.adapter.out.persistence.entity.TeacherR2dbcEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TeacherReactiveRepository extends ReactiveCrudRepository<TeacherR2dbcEntity, Long> {
}
