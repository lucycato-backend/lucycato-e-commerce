package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.userservice.model.enums.UserStatus;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLogin {
    private Long userId;

    private UserStatus userStatus;

    private final String accessToken;

    private final LocalDateTime accessTokenExpiredAt;

    private final String refreshToken;

    private final LocalDateTime refreshTokenExpiredAt;

    public static UserLogin create(
            Long userId,
            UserStatus userStatus,
            String accessToken,
            LocalDateTime accessTokenExpiredAt,
            String refreshToken,
            LocalDateTime refreshTokenExpiredAt
    ) {
        return UserLogin.builder()
                .userId(userId)
                .userStatus(userStatus)
                .accessToken(accessToken)
                .accessTokenExpiredAt(accessTokenExpiredAt)
                .refreshToken(refreshToken)
                .refreshTokenExpiredAt(refreshTokenExpiredAt)
                .build();
    }
}
