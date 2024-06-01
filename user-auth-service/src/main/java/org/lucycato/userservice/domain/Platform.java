package org.lucycato.userservice.domain;

import lombok.*;
import org.lucycato.userservice.domain.enums.NetworkType;
import org.lucycato.userservice.domain.enums.PlatformType;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Platform {
    private PlatformType platformType;

    private String platformVersion;

    private NetworkType networkType;

    private String locale;

    private LocalDateTime lastLoginAt;

    private LocalDateTime lastLogoutAt;

    public static Platform create(
            PlatformType platformType,
            String platformVersion,
            NetworkType networkType,
            String locale,
            LocalDateTime lastLoginAt,
            LocalDateTime lastLogoutAt
    ) {
        return Platform.builder()
                .platformType(platformType)
                .platformVersion(platformVersion)
                .networkType(networkType)
                .locale(locale)
                .lastLoginAt(lastLoginAt)
                .lastLogoutAt(lastLogoutAt)
                .build();
    }
}
