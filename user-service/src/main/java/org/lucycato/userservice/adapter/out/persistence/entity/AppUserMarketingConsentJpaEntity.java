package org.lucycato.userservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "app_user_marketing_consent")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class AppUserMarketingConsentJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUserJpaEntity appUserJpaEntity;

    @Enumerated(EnumType.STRING)
    private ConsentStatus consentStatus;

    @CreatedDate
    private LocalDateTime consentDate;

    @LastModifiedDate
    private LocalDateTime consentUpdateDate;

    @Enumerated(EnumType.STRING)
    private ConsentWithdrawalStatus consentWithdrawalStatus;


    public AppUserMarketingConsentJpaEntity(AppUserJpaEntity appUserJpaEntity, ConsentStatus consentStatus, LocalDateTime consentDate, LocalDateTime consentUpdateDate, ConsentWithdrawalStatus consentWithdrawalStatus) {
        this.appUserJpaEntity = appUserJpaEntity;
        this.consentStatus = consentStatus;
        this.consentDate = consentDate;
        this.consentUpdateDate = consentUpdateDate;
        this.consentWithdrawalStatus = consentWithdrawalStatus;
    }

    public enum ConsentStatus {
        CONSENTED,
        NOT_CONSENTED,
        PENDING
    }

    public enum ConsentWithdrawalStatus {
        WITHDRAWN,
        NOT_WITHDRAWN
    }
}
