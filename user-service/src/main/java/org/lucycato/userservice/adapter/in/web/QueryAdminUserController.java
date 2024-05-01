package org.lucycato.userservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.application.port.in.QueryAdminUserUseCase;
import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AppUser;
import org.lucycato.userservice.domain.DeviceManagement;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@WebAdapter
@RestController
@RequestMapping
@RequiredArgsConstructor
public class QueryAdminUserController {
    private final QueryAdminUserUseCase queryAdminUserUseCase;

    @GetMapping("api/lucycato/v1/admin/user/me")
    public AdminUser getAdminUser(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail
    ) {
        GetAdminUserCommand command = new GetAdminUserCommand(
                adminUserHeaderDetail.getAdminUserId()
        );
        return queryAdminUserUseCase.getAdminUser(command);
    }

    @GetMapping("api/lucycato/v1/admin/user/device-management/me")
    public List<DeviceManagement> getAdminUserDeviceManagementList(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail
    ) {
        GetAdminUserDeviceInfoCommand command = new GetAdminUserDeviceInfoCommand(
                adminUserHeaderDetail.getAdminUserId()
        );
        return queryAdminUserUseCase.getAdminUserDeviceManagementList(command);
    }

    @GetMapping("api/lucycato/v1/admin/user/{targetAppUserId}/app-user")
    public AppUser getAppUser(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long targetAppUserId
    ) {
        GetAppUserByAdminUserCommand command = new GetAppUserByAdminUserCommand(
                adminUserHeaderDetail.getAdminUserRoles(),
                targetAppUserId
        );
        return queryAdminUserUseCase.getAppUser(command);
    }

    @GetMapping("api/lucycato/v1/admin/user/app-user/list")
    public List<AppUser> getAppUserList(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestParam(name = "app-user-ids", defaultValue = "[]")
            List<Long> appUserIds
    ) {
        if (appUserIds.isEmpty()) {
            return queryAdminUserUseCase.getAppUserList();
        } else {
            return queryAdminUserUseCase.getAppUserListByAppUserIds(appUserIds);
        }
    }
}
