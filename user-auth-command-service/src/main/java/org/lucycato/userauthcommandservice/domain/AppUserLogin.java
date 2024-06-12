package org.lucycato.userauthcommandservice.domain;

import lombok.*;
import org.lucycato.userauthcommandservice.application.port.out.result.AppUserResult;
import org.lucycato.userauthcommandservice.application.port.out.result.IssueJwtTokenResult;

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
            IssueJwtTokenResult issueJwtTokenResult
    ) {
        AppUser appUser = AppUser.from(appUserResult);
        return AppUserLogin.builder()
                .appUser(appUser)
                .accessToken(issueJwtTokenResult.getAccessToken())
                .accessTokenExpiredAt(issueJwtTokenResult.getExpiredAccessToken())
                .refreshToken(issueJwtTokenResult.getRefreshToken())
                .refreshTokenExpiredAt(issueJwtTokenResult.getExpiredRefreshToken())
                .build();
    }
}
