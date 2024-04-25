package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.userservice.model.enums.DeviceOsType;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminUserRegisterCommand extends SelfValidating<AdminUserRegisterCommand> {
    @NotBlank
    private String phoneNumberAuthCode;

    @NotBlank
    private String nickName;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String deviceMacAddress;

    @NotBlank
    private String deviceFcmToken;

    @NotNull
    private DeviceOsType deviceOsType;

    @NotBlank
    private String deiceOsVersion;

    public AdminUserRegisterCommand(String phoneNumberAuthCode, String nickName, String name, String email, String password, String phoneNumber, String deviceMacAddress, String deviceFcmToken, DeviceOsType deviceOsType, String deiceOsVersion) {
        this.phoneNumberAuthCode = phoneNumberAuthCode;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.deviceMacAddress = deviceMacAddress;
        this.deviceFcmToken = deviceFcmToken;
        this.deviceOsType = deviceOsType;
        this.deiceOsVersion = deiceOsVersion;

        this.validateSelf();
    }
}
