package org.lucycato.userservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.userservice.adapter.in.web.request.AdminUserLoginRequest;
import org.lucycato.userservice.adapter.in.web.request.AdminUserRegisterRequest;
import org.lucycato.userservice.adapter.in.web.request.ModifyAdminUserRoleRequest;
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
                request.getDeviceInfo().getDeiceOsVersion()
        );
        return adminUserUseCase.register(command);
    }

    @PostMapping("open-api/lucycato/v1/admin/user/login")
    public AdminUserLogin loginAdminUser(@RequestBody AdminUserLoginRequest request) {
        AdminUserLoginCommand command = new AdminUserLoginCommand(
                request.getEmail(),
                request.getPassword(),
                request.getDeviceInfo().getDeviceMacAddress(),
                request.getDeviceInfo().getDeviceFcmToken(),
                request.getDeviceInfo().getDeviceOsType(),
                request.getDeviceInfo().getDeiceOsVersion()
        );
        return adminUserUseCase.login(command);
    }

    @PostMapping("api/lucycato/v1/admin/user/logout")
    public void logoutAdminUser(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail) {
        AdminUserLogoutCommand command = new AdminUserLogoutCommand(
                adminUserHeaderDetail.getAdminMemberId()
        );
        adminUserUseCase.logout(command);
    }

    @PostMapping("api/lucycato/v1/admin/user/modify-role")
    public AdminUser modifyAdminUserRole(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail, @RequestBody ModifyAdminUserRoleRequest request) {
        ModifyAdminUserRoleCommand command = new ModifyAdminUserRoleCommand(
                adminUserHeaderDetail.getAdminUserRoles(),
                request.getTargetUserId(),
                request.getRole()
        );
        return adminUserUseCase.modifyAdminUserRole(command);
    }
}