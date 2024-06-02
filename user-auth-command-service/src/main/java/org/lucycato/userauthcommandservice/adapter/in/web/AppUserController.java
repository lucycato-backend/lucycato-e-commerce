package org.lucycato.userauthcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.lucycato.userauthcommandservice.adapter.in.web.request.*;
import org.lucycato.userauthcommandservice.application.port.in.AppUserUseCase;
import org.lucycato.userauthcommandservice.application.port.in.command.*;
import org.lucycato.userauthcommandservice.domain.AppUser;
import org.lucycato.userauthcommandservice.domain.AppUserMembership;
import org.lucycato.userauthcommandservice.domain.AppUserLogin;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserUseCase appUserUseCase;

    @PostMapping("open-api/lucycato/v1/app/user/social/apple-login")
    public AppUserLogin loginByAppleSocial(
            @RequestBody
            AppUserLoginByAppleSocialRequest request
    ) {
        AppUserLoginByAppleSocialCommand command = new AppUserLoginByAppleSocialCommand(
                request.getCode(),
                request.getDevice().getDeviceMacAddress(),
                request.getDevice().getDeviceFcmToken(),
                request.getDevice().getDeviceOsType(),
                request.getDevice().getDeiceOsVersion(),
                request.getPlatform().getPlatformType(),
                request.getPlatform().getPlatformVersion(),
                request.getPlatform().getNetworkType(),
                request.getPlatform().getLocale()
        );
        return appUserUseCase.loginByAppleLogin(command);
    }

    @PostMapping("open-api/lucycato/v1/app/user/social/kakao-login")
    public AppUserLogin loginByKakaoSocial(
            @RequestBody
            AppUserLoginByKakaoSocialRequest request
    ) {
        AppUserLoginByKakaoSocialCommand command = new AppUserLoginByKakaoSocialCommand(
                request.getCode(),
                request.getDevice().getDeviceMacAddress(),
                request.getDevice().getDeviceFcmToken(),
                request.getDevice().getDeviceOsType(),
                request.getDevice().getDeiceOsVersion(),
                request.getPlatform().getPlatformType(),
                request.getPlatform().getPlatformVersion(),
                request.getPlatform().getNetworkType(),
                request.getPlatform().getLocale()
        );
        return appUserUseCase.loginByKakaoSocial(command);
    }

    @PostMapping("api/lucycato/v1/app/user/verification-phone-number")
    public AppUser loginByKakaoSocial(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail,
            @RequestBody
            VerificationPhoneNumberRequest request
    ) {
        VerifyPhoneNumberCommand command = new VerifyPhoneNumberCommand(
                appUserHeaderDetail.getAppUserId(),
                request.getPhoneNumber(),
                request.getAuthorizedCode()
        );
        return appUserUseCase.verifyPhoneNumber(command);
    }

    @PostMapping("api/lucycato/v1/app/user/login-check")
    public void loginCheck(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail,
            @RequestBody
            AppUserLoginCheckRequest request
    ) {
        AppUserLoginCheckCommand command = new AppUserLoginCheckCommand(
                appUserHeaderDetail.getAppUserId(),
                request.getDevice().getDeviceMacAddress(),
                request.getDevice().getDeviceFcmToken(),
                request.getDevice().getDeviceOsType(),
                request.getDevice().getDeiceOsVersion(),
                request.getPlatform().getPlatformType(),
                request.getPlatform().getPlatformVersion(),
                request.getPlatform().getNetworkType(),
                request.getPlatform().getLocale()
        );
        appUserUseCase.loginCheck(command);
    }

    @PostMapping("api/lucycato/v1/app/user/logout")
    public void logout(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail,
            @RequestBody
            LogoutAppUserRequest request
    ) {
        AppUserLogoutCommand command = new AppUserLogoutCommand(
                appUserHeaderDetail.getAppUserId(),
                request.getDeviceMacAddress(),
                request.getPlatformType()
        );
        appUserUseCase.logout(command);
    }

    @PostMapping("api/lucycato/v1/app/user/register-membership")
    public AppUserMembership registerAppUserMembership(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail,
            @RequestBody
            AppUserRegisterMembershipRequest request
    ) {
        RegisterAppUserMembershipCommand command = new RegisterAppUserMembershipCommand(
                appUserHeaderDetail.getAppUserId(),
                request.getMembershipGrade()
        );
        return appUserUseCase.registerAppUserMembership(command);
    }

    @PatchMapping("api/lucycato/v1/app/user/safe-remove-membership")
    public void safeRemoveAppUserMembership(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail,
            @RequestBody
            SafeRemoveAppUserMembershipRequest request
    ) {
        SafeRemoveAppUserMembershipCommand command = new SafeRemoveAppUserMembershipCommand(
                appUserHeaderDetail.getAppUserId(),
                request.getAppUserMembershipId()
        );
        appUserUseCase.safeRemoveAppUserMembership(command);
    }

    // 마케팅 약관 동의 받기
    @PostMapping("api/lucycato/v1/app/user/agree-marketing-terms")
    public void updateAgreeMarketingTerms(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail,
            @RequestBody
            AgreeMarketingTermsRequest request
    ) {
        AgreeMarketingTermsCommand command = new AgreeMarketingTermsCommand(
                appUserHeaderDetail.getAppUserId(),
                request.getIsAgreeMarketingTerms()
        );

        appUserUseCase.updateAgreeMarketingTerms(command);
    }

    @GetMapping("/test-jin")
    public String test() {
        System.out.println("test");
        return "test";
    }
}
