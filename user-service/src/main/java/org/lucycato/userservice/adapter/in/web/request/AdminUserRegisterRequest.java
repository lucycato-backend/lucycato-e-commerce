package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.model.enums.DeviceOsType;

@Getter
@NoArgsConstructor
public class AdminUserRegisterRequest {
    private String phoneNumberAuthCode;

    private String nickName;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private DeviceInfoRequest deviceInfo;
}
