package org.lucycato.userservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.userservice.adapter.in.web.request.*;
import org.lucycato.userservice.application.port.in.AdminUserUseCase;
import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AdminUserLogin;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserUseCase adminUserUseCase;

    @PostMapping("open-api/lucycato/v1/admin/user/register")
    public AdminUserLogin registerAdminUserOwnService(@RequestBody AdminUserRegisterRequest request) {
        AdminUserRegisterCommand command = new AdminUserRegisterCommand(
                request.getPhoneNumberAuthCode(),
                request.getNickName(),
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getDeviceInfo().getDeviceMacAddress(),
                request.getDeviceInfo().getDeviceFcmToken(),
                request.getDeviceInfo().getDeviceOsType(),
                request.getDeviceInfo().getDeiceOsVersion(),
                request.getAppOrDeviceInfo().getAppOrBrowserType(),
                request.getAppOrDeviceInfo().getAppOrBrowserVersion(),
                request.getAppOrDeviceInfo().getNetworkType(),
                request.getAppOrDeviceInfo().getLocale()
        );
        return adminUserUseCase.register(command);
    }

    // TODO: 어드민 아이디 중복 check API 개발

    // TODO: 아이디 찾기 API 개발

    // TODO: 비밀번호 찾기 API 개발

    @PostMapping("open-api/lucycato/v1/admin/user/login")
    public AdminUserLogin loginAdminUser(@RequestBody AdminUserLoginRequest request) {
        AdminUserLoginCommand command = new AdminUserLoginCommand(
                request.getEmail(),
                request.getPassword(),
                request.getDeviceInfo().getDeviceMacAddress(),
                request.getDeviceInfo().getDeviceFcmToken(),
                request.getDeviceInfo().getDeviceOsType(),
                request.getDeviceInfo().getDeiceOsVersion(),
                request.getAppOrDeviceInfo().getAppOrBrowserType(),
                request.getAppOrDeviceInfo().getAppOrBrowserVersion(),
                request.getAppOrDeviceInfo().getNetworkType(),
                request.getAppOrDeviceInfo().getLocale()
        );
        return adminUserUseCase.login(command);
    }

    @PostMapping("api/lucycato/v1/admin/user/login-check")
    public void checkLoginAdminUser(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail, @RequestBody AdminUserLoginCheckRequest request) {
        AdminUserLoginCheckCommand command = new AdminUserLoginCheckCommand(
                adminUserHeaderDetail.getAdminMemberId(),
                request.getDeviceInfo().getDeviceMacAddress(),
                request.getDeviceInfo().getDeviceFcmToken(),
                request.getDeviceInfo().getDeviceOsType(),
                request.getDeviceInfo().getDeiceOsVersion(),
                request.getAppOrDeviceInfo().getAppOrBrowserType(),
                request.getAppOrDeviceInfo().getAppOrBrowserVersion(),
                request.getAppOrDeviceInfo().getNetworkType(),
                request.getAppOrDeviceInfo().getLocale()
        );
        adminUserUseCase.loginCheck(command);
    }

    @PostMapping("api/lucycato/v1/admin/user/logout")
    public void logoutAdminUser(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail, @RequestBody AdminUserLogoutRequest request) {
        AdminUserLogoutCommand command = new AdminUserLogoutCommand(
                adminUserHeaderDetail.getAdminMemberId(),
                request.getDeviceMacAddress(),
                request.getAppOrBrowserType()
        );
        adminUserUseCase.logout(command);
    }

    @PatchMapping("api/lucycato/v1/admin/user/add-role")
    public AdminUser addAdminUserRole(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail, @RequestBody ModifyAdminUserRoleRequest request) {
        ModifyAdminUserRoleCommand command = new ModifyAdminUserRoleCommand(
                adminUserHeaderDetail.getAdminUserRoles(),
                request.getTargetUserId(),
                request.getRole()
        );
        return adminUserUseCase.addAdminUserRole(command);
    }

    @PatchMapping("api/lucycato/v1/admin/user/remove-role")
    public AdminUser removeAdminUserRole(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail, @RequestBody ModifyAdminUserRoleRequest request) {
        ModifyAdminUserRoleCommand command = new ModifyAdminUserRoleCommand(
                adminUserHeaderDetail.getAdminUserRoles(),
                request.getTargetUserId(),
                request.getRole()
        );
        return adminUserUseCase.removeAdminUserRole(command);
    }
}