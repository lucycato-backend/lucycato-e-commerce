package org.lucycato.userauthcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueJwtTokenResult {
    private String accessToken;

    private LocalDateTime expiredAccessToken;

    private String refreshToken;

    private LocalDateTime expiredRefreshToken;
}
