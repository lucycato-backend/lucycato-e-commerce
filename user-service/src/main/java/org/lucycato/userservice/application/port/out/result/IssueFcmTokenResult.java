package org.lucycato.userservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueFcmTokenResult {
    private String accessToken;

    private LocalDateTime expiredAccessToken;

    private String refreshToken;

    private LocalDateTime expiredRefreshToken;
}
