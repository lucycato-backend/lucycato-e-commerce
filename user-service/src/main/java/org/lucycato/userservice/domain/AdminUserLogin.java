package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.application.port.out.result.IssueJwtTokenResult;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminUserLogin {
    private AdminUser adminUser;

    private final String accessToken;

    private final LocalDateTime accessTokenExpiredAt;

    private final String refreshToken;

    private final LocalDateTime refreshTokenExpiredAt;

    public static AdminUserLogin from(AdminUserResult adminUserResult, IssueJwtTokenResult issueJwtTokenResult) {
        AdminUser adminUser = AdminUser.from(adminUserResult);
        return AdminUserLogin.builder()
                .adminUser(adminUser)
                .accessToken(issueJwtTokenResult.getAccessToken())
                .accessTokenExpiredAt(issueJwtTokenResult.getExpiredAccessToken())
                .refreshToken(issueJwtTokenResult.getRefreshToken())
                .refreshTokenExpiredAt(issueJwtTokenResult.getExpiredRefreshToken())
                .build();
    }
}
