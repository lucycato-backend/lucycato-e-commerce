package org.lucycato.notificationcommandservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.notificationcommandservice.application.port.out.QueryAppUserAuthPort;
import org.lucycato.notificationcommandservice.application.port.out.result.AppUserFirebaseTokenResult;

@ServiceAdapter
@RequiredArgsConstructor
public class UserAuthQueryServiceAdapter implements QueryAppUserAuthPort {
    @Override
    public AppUserFirebaseTokenResult getAppUserFirebaseTokenByUserId(Long userId) {
        // TODO: User Auth Query Service 에 요청 보내기
        String FirebaseToken = "";
        return new AppUserFirebaseTokenResult(userId, FirebaseToken);
    }
}
