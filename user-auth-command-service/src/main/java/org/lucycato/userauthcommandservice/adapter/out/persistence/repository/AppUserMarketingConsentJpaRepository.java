package org.lucycato.userauthcommandservice.adapter.out.persistence.repository;


import org.lucycato.userauthcommandservice.adapter.out.persistence.entity.AppUserMarketingConsentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppUserMarketingConsentJpaRepository extends JpaRepository<AppUserMarketingConsentJpaEntity, Long> {
    @Query("select e from AppUserMarketingConsentJpaEntity e where e.appUserJpaEntity.id = :appUserId")
    AppUserMarketingConsentJpaEntity findAppUserMarketingConsent(Long appUserId);
}
