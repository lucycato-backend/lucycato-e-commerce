package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.adapter.in.web.response.AppVersionCheckResponse;
import org.lucycato.userservice.application.port.in.command.AppVersionCheckCommand;

public interface MobileWebUseCase {
    AppVersionCheckResponse appVersionCheck(AppVersionCheckCommand command);
}
