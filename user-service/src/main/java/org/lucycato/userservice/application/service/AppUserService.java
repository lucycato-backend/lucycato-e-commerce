package org.lucycato.userservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.UserErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.application.port.in.AppUserUseCase;
import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.application.port.out.*;
import org.lucycato.userservice.application.port.out.result.*;
import org.lucycato.userservice.domain.*;
import org.lucycato.userservice.domain.enums.AppUserStatus;
import org.lucycato.userservice.domain.enums.SocialStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AppUserService implements AppUserUseCase {
    private final AppUserPort appUserPort;

    private final QueryAppUserPort queryAppUserPort;

    private final DeviceManagementPort deviceManagementPort;

    private final AuthPort authPort;

    private final SocialLoginPort socialLoginPort;

    @Override
    public AppUserLogin loginByAppleLogin(AppUserLoginByAppleSocialCommand command) {
        AppleAccountResult appleAccountResult = socialLoginPort.getAppleAccount(command.getCode());

        AppUserResult appUserResult;
        try {
            appUserResult = queryAppUserPort.getAppUserResultByEmail(appleAccountResult.getEmail());
        } catch (ApiExceptionImpl e) {
            appUserResult = appUserPort.registerAppUser(
                    SocialStatus.APPLE,
                    appleAccountResult.getFullName(),
                    appleAccountResult.getEmail(),
                    "",
                    AppUserStatus.ESSENTIAL_REQUIRE_ADDITIONAL
            );
        }

        if (!appUserResult.getSocialStatus().equals(SocialStatus.APPLE)) {
            throw new ApiExceptionImpl(UserErrorCodeImpl.EXIST_USER);
        }

        deviceManagementPort.modifyAppUserDeviceInfo(
                appUserResult.getAppUserId(),
                command.getCurrentAppUserDeviceMacAddress(),
                command.getCurrentAppUserDeviceFcmToken(),
                command.getCurrentAppUserDeviceOsType(),
                command.getCurrentAppUserDeiceOsVersion(),
                command.getCurrentAppUserPlatformType(),
                command.getCurrentAppUserPlatformVersion(),
                command.getCurrentAppUserNetworkType(),
                command.getCurrentAppUserLocale()
        );

        IssueJwtTokenResult issueJwtTokenResult = authPort.issueAppUserJwtToken(
                appUserResult.getAppUserId(),
                command.getCurrentAppUserDeviceMacAddress(),
                command.getCurrentAppUserPlatformType()
        );

        return AppUserLogin.from(
                appUserResult,
                issueJwtTokenResult
        );
    }

    @Override
    public AppUserLogin loginByKakaoSocial(AppUserLoginByKakaoSocialCommand command) {
        KakaoAccountResult kakaoAccountResult = socialLoginPort.getKakaoAccount(command.getCode());

        AppUserResult appUserResult;
        try {
            appUserResult = queryAppUserPort.getAppUserResultByPhoneNumber(kakaoAccountResult.getPhoneNumber());
        } catch (ApiExceptionImpl e) {
            appUserResult = appUserPort.registerAppUser(
                    SocialStatus.KAKAO,
                    kakaoAccountResult.getName(),
                    kakaoAccountResult.getEmail(),
                    kakaoAccountResult.getPhoneNumber(),
                    AppUserStatus.ENABLED
            );
        }

        if (!appUserResult.getSocialStatus().equals(SocialStatus.KAKAO)) {
            throw new ApiExceptionImpl(UserErrorCodeImpl.EXIST_USER);
        }

        deviceManagementPort.modifyAppUserDeviceInfo(
                appUserResult.getAppUserId(),
                command.getCurrentAppUserDeviceMacAddress(),
                command.getCurrentAppUserDeviceFcmToken(),
                command.getCurrentAppUserDeviceOsType(),
                command.getCurrentAppUserDeiceOsVersion(),
                command.getCurrentAppUserPlatformType(),
                command.getCurrentAppUserPlatformVersion(),
                command.getCurrentAppUserNetworkType(),
                command.getCurrentAppUserLocale()
        );

        IssueJwtTokenResult issueJwtTokenResult = authPort.issueAppUserJwtToken(
                appUserResult.getAppUserId(),
                command.getCurrentAppUserDeviceMacAddress(),
                command.getCurrentAppUserPlatformType()
        );

        return AppUserLogin.from(
                appUserResult,
                issueJwtTokenResult
        );
    }

    @Override
    public AppUser verifyPhoneNumber(VerifyPhoneNumberCommand command) {
        Boolean isVerification = authPort.verifyPhoneNumberAuthCode(command.getPhoneNumber());
        if (isVerification) {
            AppUserResult appUserResult = appUserPort.modifyAppUser(
                    command.getAppUserId(),
                    command.getPhoneNumber(),
                    AppUserStatus.ENABLED
            );
            return AppUser.from(appUserResult);
        } else {
            throw new ApiExceptionImpl(UserErrorCodeImpl.PHONE_NUMBER_VERIFICATION_NOT_MATCH);
        }
    }

    @Override
    public void loginCheck(AppUserLoginCheckCommand command) {
        deviceManagementPort.modifyAppUserDeviceInfo(
                command.getAppUserId(),
                command.getCurrentAppUserDeviceMacAddress(),
                command.getCurrentAppUserDeviceFcmToken(),
                command.getCurrentAppUserDeviceOsType(),
                command.getCurrentAppUserDeiceOsVersion(),
                command.getCurrentAppUserPlatformType(),
                command.getCurrentAppUserPlatformVersion(),
                command.getCurrentAppUserNetworkType(),
                command.getCurrentAppUserLocale()
        );
    }

    @Override
    public void logout(AppUserLogoutCommand command) {
        deviceManagementPort.modifyAppUserDeviceLogout(
                command.getAppUserId(),
                command.getCurrentAppUserDeviceMacAddress(),
                command.getCurrentAppUserPlatformType()
        );
    }

    @Override
    public AppUserMembership registerAppUserMembership(RegisterAppUserMembershipCommand command) {
        AppUserMembershipResult appUserMembershipResult = appUserPort.registerAppUserMembership(
                command.getAppUserId(),
                command.getMembershipGrade(),
                LocalDateTime.now().plusYears(1L)
        );

        return AppUserMembership.from(appUserMembershipResult);
    }

    @Override
    public void safeRemoveAppUserMembership(SafeRemoveAppUserMembershipCommand command) {
        appUserPort.safeRemoveAppUserMembership(command.getAppUserId(), command.getAppUserMembershipId());
    }
}
