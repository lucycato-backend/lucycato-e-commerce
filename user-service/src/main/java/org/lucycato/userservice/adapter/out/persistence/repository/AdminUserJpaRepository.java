package org.lucycato.userservice.adapter.out.persistence.repository;

import org.lucycato.userservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminUserJpaRepository extends JpaRepository<AdminUserJpaEntity, Long> {
    @Query("select e from AdminUserJpaEntity e where e.email = :email and e.password = :password")
    Optional<AdminUserJpaEntity> findFirstByEmailAndPassword(String email, String password);

    @Query("select e.deviceVos from AdminUserJpaEntity e where e.id = :adminUserId order by id desc")
    Optional<Object> findDeviceInfosByAppUserId(Long adminUserId);
}
