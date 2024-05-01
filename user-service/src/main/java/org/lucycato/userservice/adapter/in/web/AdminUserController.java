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

    @PostMapping("open-api/lucycato/v1/admin/user/register-own-service")
    public AdminUserLogin registerAdminUserOwnService(
            @RequestBody
            AdminUserRegisterRequest request
    ) {
        AdminUserRegisterCommand command = new AdminUserRegisterCommand(
                request.getPhoneNumberAuthCode(),
                request.getNickName(),
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getDevice().getDeviceMacAddress(),
                request.getDevice().getDeviceFcmToken(),
                request.getDevice().getDeviceOsType(),
                request.getDevice().getDeiceOsVersion(),
                request.getPlatform().getPlatformType(),
                request.getPlatform().getPlatformVersion(),
                request.getPlatform().getNetworkType(),
                request.getPlatform().getLocale()
        );
        return adminUserUseCase.register(command);
    }

    @PostMapping("open-api/lucycato/v1/admin/user/login-own-service")
    public AdminUserLogin loginAdminUser(
            @RequestBody
            AdminUserLoginRequest request
    ) {
        AdminUserLoginCommand command = new AdminUserLoginCommand(
                request.getEmail(),
                request.getPassword(),
                request.getDevice().getDeviceMacAddress(),
                request.getDevice().getDeviceFcmToken(),
                request.getDevice().getDeviceOsType(),
                request.getDevice().getDeiceOsVersion(),
                request.getPlatform().getPlatformType(),
                request.getPlatform().getPlatformVersion(),
                request.getPlatform().getNetworkType(),
                request.getPlatform().getLocale()
        );
        return adminUserUseCase.login(command);
    }

    @PostMapping("api/lucycato/v1/admin/user/login-check")
    public void checkLoginAdminUser(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestBody
            AdminUserLoginCheckRequest request
    ) {
        AdminUserLoginCheckCommand command = new AdminUserLoginCheckCommand(
                adminUserHeaderDetail.getAdminUserId(),
                request.getDevice().getDeviceMacAddress(),
                request.getDevice().getDeviceFcmToken(),
                request.getDevice().getDeviceOsType(),
                request.getDevice().getDeiceOsVersion(),
                request.getPlatform().getPlatformType(),
                request.getPlatform().getPlatformVersion(),
                request.getPlatform().getNetworkType(),
                request.getPlatform().getLocale()
        );
        adminUserUseCase.loginCheck(command);
    }

    @PostMapping("api/lucycato/v1/admin/user/logout")
    public void logoutAdminUser(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestBody
            AdminUserLogoutRequest request
    ) {
        AdminUserLogoutCommand command = new AdminUserLogoutCommand(
                adminUserHeaderDetail.getAdminUserId(),
                request.getDeviceMacAddress(),
                request.getPlatformType()
        );
        adminUserUseCase.logout(command);
    }

    @PatchMapping("api/lucycato/v1/admin/user/add-role")
    public AdminUser addAdminUserRole(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestBody
            ModifyAdminUserRoleRequest request
    ) {
        ModifyAdminUserRoleCommand command = new ModifyAdminUserRoleCommand(
                adminUserHeaderDetail.getAdminUserRoles(),
                request.getTargetUserId(),
                request.getRole()
        );
        return adminUserUseCase.addAdminUserRole(command);
    }

    @PatchMapping("api/lucycato/v1/admin/user/remove-role")
    public AdminUser removeAdminUserRole(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestBody
            ModifyAdminUserRoleRequest request
    ) {
        ModifyAdminUserRoleCommand command = new ModifyAdminUserRoleCommand(
                adminUserHeaderDetail.getAdminUserRoles(),
                request.getTargetUserId(),
                request.getRole()
        );
        return adminUserUseCase.removeAdminUserRole(command);
    }
}