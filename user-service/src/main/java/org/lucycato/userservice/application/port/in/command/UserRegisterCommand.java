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
public class UserRegisterCommand extends SelfValidating<UserRegisterCommand> {
    @NotBlank
    private String nickName;

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

    public UserRegisterCommand(String nickName, String email, String password, String phoneNumber) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;

        this.validateSelf();
    }
}
