package org.lucycato.productcommandservice.adapter.out.persistence.repository;

import org.lucycato.productcommandservice.adapter.out.persistence.entity.TeacherJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherJpaRepository extends JpaRepository<TeacherJpaEntity, Long> {
}
