package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.domain.enums.PlatformType;

@Getter
@NoArgsConstructor
public class AdminUserLogoutRequest {
    private String deviceMacAddress;

    private PlatformType platformType;
}
