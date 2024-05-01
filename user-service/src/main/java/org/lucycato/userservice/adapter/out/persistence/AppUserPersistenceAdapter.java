package org.lucycato.userservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.adapter.out.persistence.jpaentity.AppUserJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.jparepository.AppUserJpaRepository;
import org.lucycato.userservice.adapter.out.persistence.jpaentity.AppUserMembershipJpaEntity;
import org.lucycato.userservice.adapter.out.persistence.jparepository.AppUserMembershipJpaRepository;
import org.lucycato.userservice.application.port.out.AppUserPort;
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
    private final AppUserJpaRepository appUserJpaRepository;

    private final AppUserMembershipJpaRepository appUserMembershipJpaRepository;

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
    public AppUserResult modifyAppUserPhoneNumber(Long appUserId, String phoneNumber) {
        AppUserJpaEntity appUserJpaEntity = appUserJpaRepository.findById(appUserId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        appUserJpaEntity.setPhoneNumber(phoneNumber);
        AppUserJpaEntity savedAppUserJpaEntity = appUserJpaRepository.save(appUserJpaEntity);

        return AppUserResult.from(savedAppUserJpaEntity);
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

        return AppUserMembershipResult.from(savedAppUserMembershipJpaEntity);
    }

    @Override
    public AppUserMembershipResult safeRemoveAppUserMembership(Long appUserMembershipId) {
        AppUserMembershipJpaEntity appUserMembershipJpaEntity = appUserMembershipJpaRepository.findById(appUserMembershipId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        appUserMembershipJpaEntity.setStatus(AppUserMembershipStatus.REMOVED);
        AppUserMembershipJpaEntity savedAppUserMembershipJpaEntity = appUserMembershipJpaRepository.save(appUserMembershipJpaEntity);

        return AppUserMembershipResult.from(savedAppUserMembershipJpaEntity);
    }
}
