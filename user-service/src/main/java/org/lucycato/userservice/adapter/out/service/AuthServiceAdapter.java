package org.lucycato.userservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.common.security.AppUserRole;
import org.lucycato.mvc.CommonHttpClient;
import org.lucycato.userservice.application.port.out.AuthPort;
import org.lucycato.userservice.application.port.out.result.IssueFcmTokenResult;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ServiceAdapter
@RequiredArgsConstructor
public class AuthServiceAdapter implements AuthPort {
    private final CommonHttpClient commonHttpClient;

    //TODO: auth-service 구현
    @Override
    public Boolean verifyPhoneNumberAuthCode(String phoneNumberAuthCode) {
        return true;
    }

    //TODO: auth-service 구현
    @Override
    public IssueFcmTokenResult issueAdminUserFcmToken(Long adminUserId, List<AdminUserRole> adminUserRoles) {
        return IssueFcmTokenResult.builder()
                .accessToken("ACCESS JWT TOKEN")
                .expiredAccessToken(LocalDateTime.now())
                .refreshToken("REFRESH JWT TOKEN")
                .expiredAccessToken(LocalDateTime.now())
                .build();
    }

    @Override
    public List<Long> getAppUserIdsByRequestDelegationRoles(List<AdminUserRole> appUserRoles) {
        return Arrays.asList(1L, 2L);
    }
}
