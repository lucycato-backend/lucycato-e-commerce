package org.lucycato.productcommandservice.adapter.out.persistence.repository;

import org.lucycato.productcommandservice.adapter.out.persistence.entity.LectureJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<LectureJpaEntity, Long> {
}
