package org.lucycato.userservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.lucycato.userservice.adapter.in.web.request.AppUserAdditionalInfoRequest;
import org.lucycato.userservice.adapter.in.web.request.AppUserLoginByAppleSocialRequest;
import org.lucycato.userservice.adapter.in.web.request.AppUserLoginCheckRequest;
import org.lucycato.userservice.adapter.in.web.request.AppUserRegisterMembershipRequest;
import org.lucycato.userservice.application.port.in.AppUserUseCase;
import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AppUser;
import org.lucycato.userservice.domain.AppUserMembership;
import org.lucycato.userservice.domain.DeviceManagement;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserUseCase appUserUseCase;

    @PostMapping("open-api/lucycato/v1/app/user/social/apple-login")
    public AppUser loginByAppleSocial(@RequestBody AppUserLoginByAppleSocialRequest request) {
        AppUserLoginByAppleSocialCommand command = new AppUserLoginByAppleSocialCommand(
                request.getDeviceInfo().getDeviceMacAddress(),
                request.getDeviceInfo().getDeviceFcmToken(),
                request.getDeviceInfo().getDeviceOsType(),
                request.getDeviceInfo().getDeiceOsVersion(),
                request.getAppOrDeviceInfo().getAppOrBrowserType(),
                request.getAppOrDeviceInfo().getAppOrBrowserVersion(),
                request.getAppOrDeviceInfo().getNetworkType(),
                request.getAppOrDeviceInfo().getLocale()
        );
        return appUserUseCase.loginByAppleLogin(command);
    }

    @PostMapping("open-api/lucycato/v1/app/user/social/google-login")
    public AppUser loginByGoogleSocial(@RequestBody AppUserLoginByAppleSocialRequest request) {
        AppUserLoginByGoogleSocialCommand command = new AppUserLoginByGoogleSocialCommand(
                request.getDeviceInfo().getDeviceMacAddress(),
                request.getDeviceInfo().getDeviceFcmToken(),
                request.getDeviceInfo().getDeviceOsType(),
                request.getDeviceInfo().getDeiceOsVersion(),
                request.getAppOrDeviceInfo().getAppOrBrowserType(),
                request.getAppOrDeviceInfo().getAppOrBrowserVersion(),
                request.getAppOrDeviceInfo().getNetworkType(),
                request.getAppOrDeviceInfo().getLocale()
        );
        return appUserUseCase.loginByGoogleSocial(command);
    }

    @PostMapping("open-api/lucycato/v1/app/user/social/naver-login")
    public AppUser loginByNaverSocial(@RequestBody AppUserLoginByAppleSocialRequest request) {
        AppUserLoginByNaverSocialCommand command = new AppUserLoginByNaverSocialCommand(
                request.getDeviceInfo().getDeviceMacAddress(),
                request.getDeviceInfo().getDeviceFcmToken(),
                request.getDeviceInfo().getDeviceOsType(),
                request.getDeviceInfo().getDeiceOsVersion(),
                request.getAppOrDeviceInfo().getAppOrBrowserType(),
                request.getAppOrDeviceInfo().getAppOrBrowserVersion(),
                request.getAppOrDeviceInfo().getNetworkType(),
                request.getAppOrDeviceInfo().getLocale()
        );
        return appUserUseCase.loginByNaverSocial(command);
    }

    @PostMapping("open-api/lucycato/v1/app/user/social/kakao-login")
    public AppUser loginByKakaoSocial(@RequestBody AppUserLoginByAppleSocialRequest request) {
        AppUserLoginByKakaoSocialCommand command = new AppUserLoginByKakaoSocialCommand(
                request.getDeviceInfo().getDeviceMacAddress(),
                request.getDeviceInfo().getDeviceFcmToken(),
                request.getDeviceInfo().getDeviceOsType(),
                request.getDeviceInfo().getDeiceOsVersion(),
                request.getAppOrDeviceInfo().getAppOrBrowserType(),
                request.getAppOrDeviceInfo().getAppOrBrowserVersion(),
                request.getAppOrDeviceInfo().getNetworkType(),
                request.getAppOrDeviceInfo().getLocale()
        );
        return appUserUseCase.loginByKakaoSocial(command);
    }

    @PostMapping("api/lucycato/v1/app/user/login-check")
    public AppUser loginCheck(
            @AppUserHeaders AppUserHeaderDetail appUserHeaderDetail,
            @RequestBody AppUserLoginCheckRequest request
    ) {
        AppUserLoginCheckCommand command = new AppUserLoginCheckCommand(
                appUserHeaderDetail.getAppMemberId(),
                request.getDeviceInfo().getDeviceMacAddress(),
                request.getDeviceInfo().getDeviceFcmToken(),
                request.getDeviceInfo().getDeviceOsType(),
                request.getDeviceInfo().getDeiceOsVersion(),
                request.getAppOrDeviceInfo().getAppOrBrowserType(),
                request.getAppOrDeviceInfo().getAppOrBrowserVersion(),
                request.getAppOrDeviceInfo().getNetworkType(),
                request.getAppOrDeviceInfo().getLocale()
        );
        return appUserUseCase.loginCheck(command);
    }

    @PostMapping("api/lucycato/v1/app/user/logout")
    public AppUser logout(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        AppUserLogoutCommand command = new AppUserLogoutCommand(
                appUserHeaderDetail.getAppMemberId()
        );
        return appUserUseCase.logout(command);
    }

    @PatchMapping("api/lucycato/v1/app/user/additional-info")
    public AppUser enterAppUserAdditionalInfo(
            @AppUserHeaders AppUserHeaderDetail appUserHeaderDetail,
            @RequestBody AppUserAdditionalInfoRequest request
    ) {
        AppUserAdditionalInfoCommand command = new AppUserAdditionalInfoCommand(
                appUserHeaderDetail.getAppMemberId(),
                request.getName(),
                request.getNickName(),
                request.getEmail(),
                request.getPhoneNumber()
        );
        return appUserUseCase.enterAppUserAdditionalInfo(command);
    }

    @PostMapping("api/lucycato/v1/app/user/register-membership")
    public AppUser registerAppUserMembership(
            @AppUserHeaders AppUserHeaderDetail appUserHeaderDetail,
            @RequestBody AppUserRegisterMembershipRequest request
    ) {
        RegisterAppUserMembershipCommand command = new RegisterAppUserMembershipCommand(
                appUserHeaderDetail.getAppMemberId(),
                request.getMembershipGrade()
        );
        return appUserUseCase.registerAppUserMembership(command);
    }

    @PatchMapping("api/lucycato/v1/app/user/safe-remove-membership")
    public AppUser safeRemoveAppUserMembership(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        SafeRemoveAppUserMembershipCommand command = new SafeRemoveAppUserMembershipCommand(
                appUserHeaderDetail.getAppMemberId()
        );
        return appUserUseCase.safeRemoveAppUserMembership(command);
    }

    @GetMapping("api/lucycato/v1/app/user/me")
    public AppUser getAppUser(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        GetAppUserCommand command = new GetAppUserCommand(
                appUserHeaderDetail.getAppMemberId()
        );
        return appUserUseCase.getAppUser(command);
    }

    @GetMapping("api/lucycato/v1/app/user/device-management/me")
    public DeviceManagement getAppUserDeviceManagement(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        GetAppUserDeviceManagementCommand command = new GetAppUserDeviceManagementCommand(
                appUserHeaderDetail.getAppMemberId()
        );
        return appUserUseCase.getAppUserDeviceManagement(command);
    }

    @GetMapping("api/lucycato/v1/app/user/membership/me")
    public AppUserMembership getAppUserMembership(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        GetAppUserMembershipCommand command = new GetAppUserMembershipCommand(
                appUserHeaderDetail.getAppMemberId()
        );
        return appUserUseCase.getAppUserMembership(command);
    }
}
