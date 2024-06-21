package org.lucycato.productcommandservice.adapter.out.persistence.repository;

import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<CourseJpaEntity, Long> {
}
