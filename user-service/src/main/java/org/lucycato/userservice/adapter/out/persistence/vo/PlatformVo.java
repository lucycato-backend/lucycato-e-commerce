package org.lucycato.userservice.adapter.out.persistence.vo;

import lombok.*;
import org.lucycato.userservice.domain.enums.PlatformType;
import org.lucycato.userservice.domain.enums.NetworkType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlatformVo {
    private PlatformType platformType;

    private String platformVersion;

    private NetworkType networkType;

    private String locale;

    private LocalDateTime lastLoginAt;

    private LocalDateTime lastLogoutAt;
}
