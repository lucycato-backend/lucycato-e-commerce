package org.lucycato.productcommandservice.adapter.out.persistence.repository;

import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseSeriesJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSeriesJpaRepository extends JpaRepository<CourseSeriesJpaEntity, Long> {
}
