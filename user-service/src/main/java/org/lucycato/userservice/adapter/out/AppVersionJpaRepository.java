package org.lucycato.userservice.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppVersionJpaRepository extends JpaRepository<AppVersionJpaEntity, Long> {
    @Query(value = "select * from app_version order by id desc limit 1;", nativeQuery = true)
    Optional<AppVersionJpaEntity> findFirstOrderByIdDesc();
}
