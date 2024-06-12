package org.lucycato.userauthcommandservice.adapter.out.persistence.repository;

import org.lucycato.userauthcommandservice.adapter.out.persistence.entity.AppUserMembershipJpaEntity;
import org.lucycato.userauthcommandservice.domain.enums.AppUserMembershipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppUserMembershipJpaRepository extends JpaRepository<AppUserMembershipJpaEntity, Long> {
    @Query("select e from AppUserMembershipJpaEntity e where e.appUserJpaEntity.id = :appUserId and e.status = :status order by e.id desc")
    List<AppUserMembershipJpaEntity> findAppUserMembership(Long appUserId, AppUserMembershipStatus status);

}
