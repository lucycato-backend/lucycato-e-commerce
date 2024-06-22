package org.lucycato.boardcommandservice.adapter.out.persistence.repository;

import org.lucycato.boardcommandservice.adapter.out.persistence.entity.CourseReviewJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseReviewJpaRepository extends JpaRepository<CourseReviewJpaEntity, Long> {
}
