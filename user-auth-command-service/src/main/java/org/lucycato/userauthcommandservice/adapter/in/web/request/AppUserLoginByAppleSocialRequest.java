package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppUserLoginByAppleSocialRequest {
    private String code;

    private DeviceRequest device;

    private PlatformRequest platform;
}
