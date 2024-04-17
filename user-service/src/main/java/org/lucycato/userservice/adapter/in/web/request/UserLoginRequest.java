package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.model.enums.DeviceOsType;

@Getter
@NoArgsConstructor
public class UserLoginRequest {
    private String email;

    private String password;

    private String deviceMacAddress;

    private String deviceFcmToken;

    private DeviceOsType deviceOsType;

    private String deiceOsVersion;
}
