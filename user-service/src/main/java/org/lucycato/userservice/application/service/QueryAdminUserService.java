package org.lucycato.userservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.userservice.application.port.in.QueryAdminUserUseCase;
import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.application.port.out.AuthPort;
import org.lucycato.userservice.application.port.out.ProductPort;
import org.lucycato.userservice.application.port.out.QueryAdminUserPort;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.application.port.out.result.AppUserResult;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AppUser;
import org.lucycato.userservice.domain.DeviceManagement;
import org.lucycato.userservice.model.enums.UserStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QueryAdminUserService implements QueryAdminUserUseCase {
    private final QueryAdminUserPort queryAdminUserPort;

    private final AuthPort authPort;

    private final ProductPort productPort;

    @Override
    public AdminUser getAdminUser(GetAdminUserCommand command) {
        AdminUserResult adminUserResult = queryAdminUserPort.getAdminUser(command.getTargetAdminUserId());
        return AdminUser.create(
                adminUserResult.getAdminUserId(),
                adminUserResult.getName(),
                adminUserResult.getEmail(),
                adminUserResult.getPhoneNumber(),
                adminUserResult.getImageUrl(),
                adminUserResult.getAdminUserRoles(),
                adminUserResult.getCreatedAt(),
                adminUserResult.getModifiedAt()
        );
    }

    @Override
    public DeviceManagement getAdminUserDevicemanagement(GetAdminUserDeviceInfoCommand command) {
        AdminUserResult adminUserResult = queryAdminUserPort.getAdminUser(command.getAdminUserId());
        return DeviceManagement.create(
                command.getAdminUserId(),
                UserStatus.ADMIN,
                adminUserResult.getDeviceInfos()
        );
    }

    @Override
    public AppUser getAppUser(GetAppUserByAdminUserCommand command) {
        AppUserResult appUserResult = queryAdminUserPort.getAppUser(command.getTargetAppUserId());
        return AppUser.create(
                appUserResult.getAppUserId(),
                appUserResult.getSocialStatus(),
                appUserResult.getNickName(),
                appUserResult.getName(),
                appUserResult.getEmail(),
                appUserResult.getPhoneNumber(),
                appUserResult.getImageUrl(),
                appUserResult.getGrade(),
                appUserResult.getBadges(),
                appUserResult.getCreatedAt(),
                appUserResult.getModifiedAt()
        );
    }

    @Override
    public List<AppUser> getAppUserList() {
        List<AppUserResult> appUserResults = queryAdminUserPort.getAppUserList();
        return appUserResults.stream().map(appUserResult ->
                AppUser.create(
                        appUserResult.getAppUserId(),
                        appUserResult.getSocialStatus(),
                        appUserResult.getNickName(),
                        appUserResult.getName(),
                        appUserResult.getEmail(),
                        appUserResult.getPhoneNumber(),
                        appUserResult.getImageUrl(),
                        appUserResult.getGrade(),
                        appUserResult.getBadges(),
                        appUserResult.getCreatedAt(),
                        appUserResult.getModifiedAt()
                )
        ).toList();
    }

    @Override
    public List<AppUser> getAppUserByLectureId(GetAppUserListByLectureIdsCommand command) {
        List<Long> userIds = productPort.getAppUserIdsByLectureIds(command.getTargetLectureIds());
        List<AppUserResult> appUserResults = queryAdminUserPort.getAppUserListByUserIds(userIds);
        return appUserResults.stream().map(appUserResult ->
                AppUser.create(
                        appUserResult.getAppUserId(),
                        appUserResult.getSocialStatus(),
                        appUserResult.getNickName(),
                        appUserResult.getName(),
                        appUserResult.getEmail(),
                        appUserResult.getPhoneNumber(),
                        appUserResult.getImageUrl(),
                        appUserResult.getGrade(),
                        appUserResult.getBadges(),
                        appUserResult.getCreatedAt(),
                        appUserResult.getModifiedAt()
                )
        ).toList();
    }

    @Override
    public List<AppUser> getAppUserByTeacherId(GetAppUserListByTeacherIdsCommand command) {
        List<Long> userIds = productPort.getAppUserIdsByTeacherIds(command.getTargetTeacherIds());
        List<AppUserResult> appUserResults = queryAdminUserPort.getAppUserListByUserIds(userIds);
        return appUserResults.stream().map(appUserResult ->
                AppUser.create(
                        appUserResult.getAppUserId(),
                        appUserResult.getSocialStatus(),
                        appUserResult.getNickName(),
                        appUserResult.getName(),
                        appUserResult.getEmail(),
                        appUserResult.getPhoneNumber(),
                        appUserResult.getImageUrl(),
                        appUserResult.getGrade(),
                        appUserResult.getBadges(),
                        appUserResult.getCreatedAt(),
                        appUserResult.getModifiedAt()
                )
        ).toList();
    }

    @Override
    public List<AppUser> getAppUserListByRequestDelegationRoles(GetAppUserByRequestDelegationRolesCommand command) {
        List<Long> userIds = authPort.getAppUserIdsByRequestDelegationRoles(command.getRequestDelegationRoles());
        List<AppUserResult> appUserResults = queryAdminUserPort.getAppUserListByUserIds(userIds);
        return appUserResults.stream().map(appUserResult ->
                AppUser.create(
                        appUserResult.getAppUserId(),
                        appUserResult.getSocialStatus(),
                        appUserResult.getNickName(),
                        appUserResult.getName(),
                        appUserResult.getEmail(),
                        appUserResult.getPhoneNumber(),
                        appUserResult.getImageUrl(),
                        appUserResult.getGrade(),
                        appUserResult.getBadges(),
                        appUserResult.getCreatedAt(),
                        appUserResult.getModifiedAt()
                )
        ).toList();
    }
}
