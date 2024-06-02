package org.lucycato.userauthcommandservice.application.port.in;

import org.lucycato.userauthcommandservice.application.port.in.command.GetAppUserCommand;
import org.lucycato.userauthcommandservice.application.port.in.command.GetAppUserDeviceManagementCommand;
import org.lucycato.userauthcommandservice.domain.AppUser;
import org.lucycato.userauthcommandservice.domain.DeviceManagement;

import java.util.List;

public interface QueryAppUserUseCase {

    AppUser getAppUser(GetAppUserCommand command);

    List<DeviceManagement> getAppUserDeviceManagementList(GetAppUserDeviceManagementCommand command);
}
