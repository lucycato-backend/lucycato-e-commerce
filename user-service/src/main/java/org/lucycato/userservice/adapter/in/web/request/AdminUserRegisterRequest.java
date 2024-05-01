package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminUserRegisterRequest {
    private String phoneNumberAuthCode;

    private String nickName;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private DeviceRequest device;

    private PlatformRequest platform;
}
