package org.lucycato.notificationcommandservice.application.port.out;

import org.lucycato.notificationcommandservice.application.port.out.result.AppUserFirebaseTokenResult;

public interface QueryAppUserAuthPort {

    AppUserFirebaseTokenResult getAppUserFirebaseTokenByUserId(Long userId);
}
