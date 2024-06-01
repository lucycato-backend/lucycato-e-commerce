package org.lucycato.userservice.adapter.out.persistence.repository;

import org.lucycato.userservice.adapter.out.persistence.entity.AppUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppUserJpaRepository extends JpaRepository<AppUserJpaEntity, Long> {
    @Query("select e from AppUserJpaEntity e where e.id in :appUserIds")
    List<AppUserJpaEntity> findByIds(List<Long> appUserIds);

    @Query("select e from AppUserJpaEntity e where e.email = :email")
    Optional<AppUserJpaEntity> findFirstByEmail(String email);

    @Query("select e from AppUserJpaEntity e where e.phoneNumber = :phoneNumber")
    Optional<AppUserJpaEntity> findFirstByPhoneNumber(String phoneNumber);

    @Query("select e.deviceVos from AppUserJpaEntity e where e.id = :appUserId order by id desc")
    Optional<Object> findDeviceInfosByAppUserId(Long appUserId);
}
