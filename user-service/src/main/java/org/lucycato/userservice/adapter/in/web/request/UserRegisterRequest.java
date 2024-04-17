package org.lucycato.userservice.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.resource.transaction.spi.SynchronizationRegistryImplementor;
import org.lucycato.userservice.model.enums.DeviceOsType;

@Getter
@NoArgsConstructor
public class UserRegisterRequest {
    private String nickName;

    private String email;

    private String password;

    private String phoneNumber;

    private String deviceMacAddress;

    private String deviceFcmToken;

    private DeviceOsType deviceOsType;

    private String deiceOsVersion;
}
