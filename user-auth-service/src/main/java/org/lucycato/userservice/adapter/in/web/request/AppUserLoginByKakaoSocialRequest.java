package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppUserLoginByKakaoSocialRequest {
    private String code;

    private DeviceRequest device;

    private PlatformRequest platform;
}
