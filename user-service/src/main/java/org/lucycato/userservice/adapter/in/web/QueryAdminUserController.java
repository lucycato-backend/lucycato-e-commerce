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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping
@RequiredArgsConstructor
public class QueryAdminUserController {
    private final QueryAdminUserUseCase queryAdminUserUseCase;

    @GetMapping("api/lucycato/v1/admin/user/me")
    public AdminUser getAdminUser(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail) {
        GetAdminUserCommand command = new GetAdminUserCommand(
                adminUserHeaderDetail.getAdminMemberId()
        );
        return queryAdminUserUseCase.getAdminUser(command);
    }

    @GetMapping("api/lucycato/v1/admin/user/{targetAppUserId}/app-user")
    public AppUser getAppUser(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail, @PathVariable Long targetAppUserId) {
        GetAppUserCommand command = new GetAppUserCommand(
                adminUserHeaderDetail.getAdminUserRoles(),
                targetAppUserId
        );
        return queryAdminUserUseCase.getAppUser(command);
    }

    @GetMapping("api/lucycato/v1/admin/user/app-user/list")
    public List<AppUser> getAppUserList(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail) {
        return queryAdminUserUseCase.getAppUserList();
    }

    @GetMapping("api/lucycato/v1/admin/user/app-user/list/by-lecture")
    public List<AppUser> getLectureAppUserListByLectureIds(
            @AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestParam(name = "targetLectureIds") List<Long> targetLectureIds
    ) {
        GetAppUserListByLectureIdsCommand command = new GetAppUserListByLectureIdsCommand(
                adminUserHeaderDetail.getAdminUserRoles(),
                targetLectureIds
        );
        return queryAdminUserUseCase.getAppUserByLectureId(command);
    }

    @GetMapping("api/lucycato/v1/admin/user/app-user/list/by-teacher")
    public List<AppUser> getLectureAppUserListByTeacherIds(
            @AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestParam(name = "targetTeacherIds") List<Long> targetTeacherIds
    ) {
        GetAppUserListByTeacherIdsCommand command = new GetAppUserListByTeacherIdsCommand(
                adminUserHeaderDetail.getAdminUserRoles(),
                targetTeacherIds
        );
        return queryAdminUserUseCase.getAppUserByTeacherId(command);
    }

    @GetMapping("api/lucycato/v1/admin/user/app-user/list/by-request-delegation-roles")
    public List<AppUser> getAppUserListByRequestDelegationRoles(
            @AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestParam(name = "roles") List<AdminUserRole> requestDelegationRoles
    ) {
        GetAppUserByRequestDelegationRolesCommand command = new GetAppUserByRequestDelegationRolesCommand(
                adminUserHeaderDetail.getAdminUserRoles(),
                requestDelegationRoles
        );
        return queryAdminUserUseCase.getAppUserListByRequestDelegationRoles(command);
    }
}
