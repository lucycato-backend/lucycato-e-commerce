package org.lucycato.userauthcommandservice.application.port.in.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.userauthcommandservice.domain.enums.PlatformType;
import org.lucycato.userauthcommandservice.domain.enums.DeviceOsType;
import org.lucycato.userauthcommandservice.domain.enums.NetworkType;

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
    private String currentAdminUserDeviceMacAddress;

    @NotBlank
    private String currentAdminUserDeviceFcmToken;

    @NotNull
    private DeviceOsType currentAdminUserDeviceOsType;

    @NotBlank
    private String currentAdminUserDeiceOsVersion;

    @NotNull
    private PlatformType currentAdminUserPlatformType;

    @NotBlank
    private String currentAdminUserPlatformVersion;

    @NotNull
    private NetworkType currentAdminUserNetworkType;

    @NotBlank
    private String currentAdminUserLocale;


    public AdminUserRegisterCommand(String phoneNumberAuthCode, String nickName, String name, String email, String password, String phoneNumber, String currentAdminUserDeviceMacAddress, String currentAdminUserDeviceFcmToken, DeviceOsType currentAdminUserDeviceOsType, String currentAdminUserDeiceOsVersion, PlatformType currentAdminUserPlatformType, String currentAdminUserPlatformVersion, NetworkType currentAdminUserNetworkType, String currentAdminUserLocale) {
        this.phoneNumberAuthCode = phoneNumberAuthCode;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.currentAdminUserDeviceMacAddress = currentAdminUserDeviceMacAddress;
        this.currentAdminUserDeviceFcmToken = currentAdminUserDeviceFcmToken;
        this.currentAdminUserDeviceOsType = currentAdminUserDeviceOsType;
        this.currentAdminUserDeiceOsVersion = currentAdminUserDeiceOsVersion;
        this.currentAdminUserPlatformType = currentAdminUserPlatformType;
        this.currentAdminUserPlatformVersion = currentAdminUserPlatformVersion;
        this.currentAdminUserNetworkType = currentAdminUserNetworkType;
        this.currentAdminUserLocale = currentAdminUserLocale;

        this.validateSelf();
    }
}
