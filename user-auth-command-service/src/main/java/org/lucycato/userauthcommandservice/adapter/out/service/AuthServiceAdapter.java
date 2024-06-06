package org.lucycato.userauthcommandservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.mvc.CommonRestTemplate;
import org.lucycato.userauthcommandservice.application.port.out.AuthPort;
import org.lucycato.userauthcommandservice.application.port.out.result.IssueJwtTokenResult;
import org.lucycato.userauthcommandservice.domain.enums.PlatformType;

import java.time.LocalDateTime;
import java.util.List;

import org.lucycato.userauthcommandservice.util.JwtsUtil;

@ServiceAdapter
@RequiredArgsConstructor
public class AuthServiceAdapter implements AuthPort {
    private final CommonRestTemplate commonRestTemplate;

    private static final String SECRET_KEY = "your-secret-key"; // 랜덤한 값으로 변경하세요

    //TODO: auth-service 구현
    @Override
    public Boolean verifyPhoneNumberAuthCode(String phoneNumberAuthCode) {
        return true;
    }

    @Override
    public IssueJwtTokenResult issueAdminUserJwtToken(Long adminUserId, List<AdminUserRole> adminUserRoles, String currentDeviceMacAddress, PlatformType currentPlatformType) {
        return IssueJwtTokenResult.builder()
                .accessToken(JwtsUtil.creatrJwtToken(currentDeviceMacAddress))
                .expiredAccessToken(LocalDateTime.now())
                .refreshToken(JwtsUtil.creatrRefreshToken())
                .expiredAccessToken(LocalDateTime.now())
                .build();
    }

    @Override
    public IssueJwtTokenResult issueAppUserJwtToken(Long adminUserId, String currentDeviceMacAddress, PlatformType currentPlatformType) {
        return IssueJwtTokenResult.builder()
                .accessToken(JwtsUtil.creatrJwtToken(currentDeviceMacAddress))
                .expiredAccessToken(LocalDateTime.now())
                .refreshToken(JwtsUtil.creatrRefreshToken())
                .expiredAccessToken(LocalDateTime.now())
                .build();
    }
}
