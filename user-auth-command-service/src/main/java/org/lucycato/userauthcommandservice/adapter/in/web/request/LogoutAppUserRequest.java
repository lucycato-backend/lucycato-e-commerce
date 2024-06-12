package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userauthcommandservice.domain.enums.PlatformType;

@Getter
@NoArgsConstructor
public class LogoutAppUserRequest {
    private String deviceMacAddress;

    private PlatformType platformType;
}
