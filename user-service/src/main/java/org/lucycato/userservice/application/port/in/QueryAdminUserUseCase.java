package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AppUser;
import org.lucycato.userservice.domain.DeviceManagement;

import java.util.List;

public interface QueryAdminUserUseCase {
    AdminUser getAdminUser(GetAdminUserCommand command);

    DeviceManagement getAdminUserDevicemanagement(GetAdminUserDeviceInfoCommand command);

    AppUser getAppUser(GetAppUserCommand command);

    List<AppUser> getAppUserList();

    List<AppUser> getAppUserByLectureId(GetAppUserListByLectureIdsCommand command);

    List<AppUser> getAppUserByTeacherId(GetAppUserListByTeacherIdsCommand command);

    List<AppUser> getAppUserListByRequestDelegationRoles(GetAppUserByRequestDelegationRolesCommand command);
}
