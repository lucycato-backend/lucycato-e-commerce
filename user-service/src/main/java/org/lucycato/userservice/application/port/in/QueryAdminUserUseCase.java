package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AppUser;
import org.lucycato.userservice.domain.DeviceManagement;

import java.util.List;

public interface QueryAdminUserUseCase {
    AdminUser getAdminUser(GetAdminUserCommand command);

    List<DeviceManagement> getAdminUserDeviceManagementList(GetAdminUserDeviceInfoCommand command);

    AppUser getAppUser(GetAppUserByAdminUserCommand command);

    List<AppUser> getAppUserList();

    List<AppUser> getAppUserListByAppUserIds(List<Long> userIds);

    boolean checkAdminUserEmail(AdminUserEmailCheckCommand command);
}
