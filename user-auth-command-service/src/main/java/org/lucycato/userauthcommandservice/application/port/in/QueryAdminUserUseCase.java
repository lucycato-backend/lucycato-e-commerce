package org.lucycato.userauthcommandservice.application.port.in;

import org.lucycato.userauthcommandservice.application.port.in.command.*;
import org.lucycato.userauthcommandservice.domain.AdminUser;
import org.lucycato.userauthcommandservice.domain.AdminUserProfile;
import org.lucycato.userauthcommandservice.domain.AppUser;
import org.lucycato.userauthcommandservice.domain.DeviceManagement;

import java.util.List;

public interface QueryAdminUserUseCase {
    AdminUser getAdminUser(GetAdminUserCommand command);

    List<DeviceManagement> getAdminUserDeviceManagementList(GetAdminUserDeviceInfoCommand command);

    AppUser getAppUser(GetAppUserByAdminUserCommand command);

    List<AppUser> getAppUserList();

    List<AppUser> getAppUserListByAppUserIds(List<Long> userIds);

    boolean certificateAdminUser(AdminUserCertificationCommand command);

    AdminUserProfile getAdminUserProfile(FindAdminUserIdCommand command);
}
