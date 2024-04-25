package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminUserLoginCheckRequest {
    private DeviceInfoRequest deviceInfo;

    private AppOrDeviceInfoRequest appOrDeviceInfo;
}
