package org.lucycato.productcommandservice.adapter.out.persistence.repository;

import org.lucycato.productcommandservice.adapter.out.persistence.entity.TextEBookJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextEBookJpaRepository extends JpaRepository<TextEBookJpaEntity, Long> {
}
