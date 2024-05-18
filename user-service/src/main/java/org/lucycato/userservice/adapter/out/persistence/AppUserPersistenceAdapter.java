package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.mvc.CommonRedisTemplate;
import org.lucycato.userservice.adapter.out.persistence.entity.AppUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.entity.AppUserMarketingConsentJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.entity.AppUserMembershipJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.repository.AppUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.repository.AppUserMarketingConsentJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.repository.AppUserMembershipJpaRepository;
import org.lucycato.userservice.application.port.out.AppUserPort;
import org.lucycato.userservice.application.port.out.result.AppUserMarketingConsentResult;
import org.lucycato.userservice.application.port.out.result.AppUserMembershipResult;
import org.lucycato.userservice.application.port.out.result.AppUserResult;
import org.lucycato.userservice.domain.enums.AppUserMembershipStatus;
import org.lucycato.userservice.domain.enums.AppUserStatus;
import org.lucycato.userservice.domain.enums.MembershipGrade;
import org.lucycato.userservice.domain.enums.SocialStatus;

import java.time.LocalDateTime;

@PersistenceAdapter
@RequiredArgsConstructor
public class AppUserPersistenceAdapter implements AppUserPort {
    private final String APP_USER_REDIS_KEY = "app-users:%d";

    private final AppUserJpaRepository appUserJpaRepository;

    private final AppUserMembershipJpaRepository appUserMembershipJpaRepository;

    private final CommonRedisTemplate commonRedisTemplate;

    private final AppUserMarketingConsentJpaRepository appUserMarketingConsentJpaRepository;

    @Override
    public AppUserResult registerAppUser(
            SocialStatus socialStatus,
            String name,
            String email,
            String phoneNumber,
            AppUserStatus status
    ) {
        AppUserJpaEntity appUserJpaEntity = new AppUserJpaEntity(
                socialStatus,
                name,
                email,
                phoneNumber,
                status
        );

        AppUserJpaEntity savedAppUserJpaEntity = appUserJpaRepository.save(appUserJpaEntity);

        return AppUserResult.from(savedAppUserJpaEntity);
    }

    @Override
    public AppUserResult modifyAppUser(
            Long appUserId,
            String phoneNumber,
            AppUserStatus appUserStatus
    ) {
        AppUserJpaEntity appUserJpaEntity = appUserJpaRepository.findById(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        appUserJpaEntity.setPhoneNumber(phoneNumber);
        appUserJpaEntity.setStatus(appUserStatus);
        AppUserJpaEntity savedAppUserJpaEntity = appUserJpaRepository.save(appUserJpaEntity);

        commonRedisTemplate.delete(APP_USER_REDIS_KEY.formatted(appUserId));

        return AppUserResult.from(savedAppUserJpaEntity);
    }

    @Override
    public void expiredAppUser(Long appUserId) {
        commonRedisTemplate.delete(APP_USER_REDIS_KEY.formatted(appUserId));
    }

    @Override
    public AppUserMembershipResult registerAppUserMembership(
            Long appUserId,
            MembershipGrade membershipGrade,
            LocalDateTime expiredAt
    ) {
        AppUserJpaEntity appUserJpaEntity = appUserJpaRepository.findById(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        AppUserMembershipJpaEntity appUserMembershipJpaEntity = new AppUserMembershipJpaEntity(
                appUserJpaEntity,
                membershipGrade,
                AppUserMembershipStatus.OPERATOR,
                expiredAt
        );

        AppUserMembershipJpaEntity savedAppUserMembershipJpaEntity = appUserMembershipJpaRepository.save(appUserMembershipJpaEntity);

        commonRedisTemplate.delete(APP_USER_REDIS_KEY.formatted(appUserId));

        return AppUserMembershipResult.from(savedAppUserMembershipJpaEntity);
    }

    @Override
    public AppUserMembershipResult safeRemoveAppUserMembership(Long appUserId, Long appUserMembershipId) {
        AppUserMembershipJpaEntity appUserMembershipJpaEntity = appUserMembershipJpaRepository.findById(appUserMembershipId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        appUserMembershipJpaEntity.setStatus(AppUserMembershipStatus.REMOVED);
        AppUserMembershipJpaEntity savedAppUserMembershipJpaEntity = appUserMembershipJpaRepository.save(appUserMembershipJpaEntity);

        commonRedisTemplate.delete(APP_USER_REDIS_KEY.formatted(appUserId));

        return AppUserMembershipResult.from(savedAppUserMembershipJpaEntity);
    }

    @Override
    public AppUserMarketingConsentResult updateAgreeMarketingTerms(Long appUserId, Boolean agreeMarketingTerms) {
        AppUserJpaEntity appUserJpaEntity = appUserJpaRepository.findById(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        AppUserMarketingConsentJpaEntity appUserMarketingConsent = new AppUserMarketingConsentJpaEntity(
                appUserJpaEntity,
                agreeMarketingTerms ? AppUserMarketingConsentJpaEntity.ConsentStatus.CONSENTED : AppUserMarketingConsentJpaEntity.ConsentStatus.NOT_CONSENTED,
                LocalDateTime.now(),
                LocalDateTime.now(),
                AppUserMarketingConsentJpaEntity.ConsentWithdrawalStatus.NOT_WITHDRAWN
        );
        AppUserMarketingConsentJpaEntity appUserMarketingConsentJpaEntity = appUserMarketingConsentJpaRepository.save(appUserMarketingConsent);

        return AppUserMarketingConsentResult.from(appUserMarketingConsentJpaEntity);

    }


}
