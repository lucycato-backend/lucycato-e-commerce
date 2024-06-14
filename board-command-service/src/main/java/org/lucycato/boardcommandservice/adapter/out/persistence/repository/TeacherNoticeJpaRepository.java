package org.lucycato.boardcommandservice.adapter.out.persistence.repository;

import org.lucycato.boardcommandservice.adapter.out.persistence.entity.TeacherNoticeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherNoticeJpaRepository extends JpaRepository<TeacherNoticeJpaEntity,Long> {
}
