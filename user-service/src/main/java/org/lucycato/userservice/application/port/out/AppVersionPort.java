package org.lucycato.userservice.application.port.out;

import org.lucycato.userservice.application.port.out.response.AppVersionResponse;

public interface AppVersionPort {
    AppVersionResponse getStandardAppVersion();
}
