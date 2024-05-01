package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.userservice.application.port.out.result.AppUserResult;
import org.lucycato.userservice.application.port.out.result.IssueJwtTokenResult;
import org.lucycato.userservice.domain.enums.PlatformType;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUserLogin {
    private AppUser appUser;

    private final String accessToken;

    private final LocalDateTime accessTokenExpiredAt;

    private final String refreshToken;

    private final LocalDateTime refreshTokenExpiredAt;

    public static AppUserLogin from(
            AppUserResult appUserResult,
            IssueJwtTokenResult issueJwtTokenResult,
            String currentAppUserDeviceMacAddress,
            PlatformType currentAppUserPlatformType
    ) {
        AppUser appUser = AppUser.from(appUserResult, currentAppUserDeviceMacAddress, currentAppUserPlatformType);
        return AppUserLogin.builder()
                .appUser(appUser)
                .accessToken(issueJwtTokenResult.getAccessToken())
                .accessTokenExpiredAt(issueJwtTokenResult.getExpiredAccessToken())
                .refreshToken(issueJwtTokenResult.getRefreshToken())
                .refreshTokenExpiredAt(issueJwtTokenResult.getExpiredRefreshToken())
                .build();
    }
}
