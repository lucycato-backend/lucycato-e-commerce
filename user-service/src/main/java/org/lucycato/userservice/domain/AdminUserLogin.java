package org.lucycato.userservice.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminUserLogin {
    private Long adminUserId;

    private final String accessToken;

    private final LocalDateTime accessTokenExpiredAt;

    private final String refreshToken;

    private final LocalDateTime refreshTokenExpiredAt;

    public static AdminUserLogin create(
            Long adminUserId,
            String accessToken,
            LocalDateTime accessTokenExpiredAt,
            String refreshToken,
            LocalDateTime refreshTokenExpiredAt
    ) {
        return AdminUserLogin.builder()
                .adminUserId(adminUserId)
                .accessToken(accessToken)
                .accessTokenExpiredAt(accessTokenExpiredAt)
                .refreshToken(refreshToken)
                .refreshTokenExpiredAt(refreshTokenExpiredAt)
                .build();
    }
}
