package org.lucycato.userservice.adapter.out.persistence;

import org.lucycato.userservice.adapter.out.persistence.entity.AppUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AppUserJpaRepository extends JpaRepository<AppUserJpaEntity, Long> {
    @Query("select e from AppUserJpaEntity e where e.id in :appUserIds")
    List<AppUserJpaEntity> findByIds(List<Long> appUserIds);
}
