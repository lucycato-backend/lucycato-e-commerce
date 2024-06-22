package org.lucycato.boardcommandservice.adapter.out.persistence.repository;

import org.lucycato.boardcommandservice.adapter.out.persistence.entity.ExamStoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamStoryJpaRepository extends JpaRepository<ExamStoryJpaEntity,Long> {
}
