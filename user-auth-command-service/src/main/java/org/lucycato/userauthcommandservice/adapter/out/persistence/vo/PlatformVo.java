package org.lucycato.userauthcommandservice.adapter.out.persistence.vo;

import lombok.*;
import org.lucycato.userauthcommandservice.domain.enums.PlatformType;
import org.lucycato.userauthcommandservice.domain.enums.NetworkType;

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
