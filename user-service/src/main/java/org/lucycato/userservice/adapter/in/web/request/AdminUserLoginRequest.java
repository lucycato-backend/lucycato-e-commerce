package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.model.enums.DeviceOsType;

@Getter
@NoArgsConstructor
public class AdminUserLoginRequest {
    private String email;

    private String password;

    private DeviceInfoRequest deviceInfo;

    private AppOrDeviceInfoRequest appOrDeviceInfo;
}
