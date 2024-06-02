package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminUserLoginRequest {
    private String email;

    private String password;

    private DeviceRequest device;

    private PlatformRequest platform;
}
