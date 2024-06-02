package org.lucycato.userauthcommandservice.application.port.out.result;

import lombok.Builder;
import org.lucycato.userauthcommandservice.adapter.out.persistence.entity.AppUserJpaEntity;
import org.lucycato.userauthcommandservice.adapter.out.persistence.entity.AppUserMarketingConsentJpaEntity;

import java.time.LocalDateTime;

@Builder
public class AppUserMarketingConsentResult {
    private final AppUserJpaEntity appUserJpaEntity;

    private final AppUserMarketingConsentJpaEntity.ConsentStatus consentStatus;

    private final LocalDateTime consentDate;

    private final LocalDateTime consentUpdateDate;

    private final AppUserMarketingConsentJpaEntity.ConsentWithdrawalStatus consentWithdrawalStatus;
    public static AppUserMarketingConsentResult from(AppUserMarketingConsentJpaEntity entity) {
        return AppUserMarketingConsentResult.builder()
                .appUserJpaEntity(entity.getAppUserJpaEntity())
                .consentStatus(entity.getConsentStatus())
                .consentDate(entity.getConsentDate())
                .consentUpdateDate(entity.getConsentUpdateDate())
                .consentWithdrawalStatus(entity.getConsentWithdrawalStatus())
                .build();
    }
}
