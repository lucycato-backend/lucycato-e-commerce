package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.application.port.in.command.GetAppUserCommand;
import org.lucycato.userservice.application.port.in.command.GetAppUserDeviceManagementCommand;
import org.lucycato.userservice.application.port.in.command.GetAppUserMembershipCommand;
import org.lucycato.userservice.domain.AppUser;
import org.lucycato.userservice.domain.AppUserMembership;
import org.lucycato.userservice.domain.DeviceManagement;

import java.util.List;

public interface QueryAppUserUseCase {

    AppUser getAppUser(GetAppUserCommand command);

    List<DeviceManagement> getAppUserDeviceManagementList(GetAppUserDeviceManagementCommand command);
}
