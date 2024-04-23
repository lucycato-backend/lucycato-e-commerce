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
public class AdminUserLoginCommand extends SelfValidating<AdminUserLoginCommand> {
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String deviceMacAddress;

    @NotBlank
    private String deviceFcmToken;

    @NotNull
    private DeviceOsType deviceOsType;

    @NotBlank
    private String deiceOsVersion;

    public AdminUserLoginCommand(String email, String password, String deviceMacAddress, String deviceFcmToken, DeviceOsType deviceOsType, String deiceOsVersion) {
        this.email = email;
        this.password = password;
        this.deviceMacAddress = deviceMacAddress;
        this.deviceFcmToken = deviceFcmToken;
        this.deviceOsType = deviceOsType;
        this.deiceOsVersion = deiceOsVersion;

        this.validateSelf();
    }
}
