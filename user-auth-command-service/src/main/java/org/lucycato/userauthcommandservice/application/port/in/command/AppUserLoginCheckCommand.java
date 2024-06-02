package org.lucycato.userauthcommandservice.application.port.in.command;


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
public class AppUserLoginCheckCommand extends SelfValidating<AppUserLoginCheckCommand> {
    @NotNull
    private Long appUserId;

    @NotBlank
    private String currentAppUserDeviceMacAddress;

    @NotBlank
    private String currentAppUserDeviceFcmToken;

    @NotNull
    private DeviceOsType currentAppUserDeviceOsType;

    @NotBlank
    private String currentAppUserDeiceOsVersion;

    @NotNull
    private PlatformType currentAppUserPlatformType;

    @NotBlank
    private String currentAppUserPlatformVersion;

    @NotNull
    private NetworkType currentAppUserNetworkType;

    @NotBlank
    private String currentAppUserLocale;

    public AppUserLoginCheckCommand(Long appUserId, String currentAppUserDeviceMacAddress, String currentAppUserDeviceFcmToken, DeviceOsType currentAppUserDeviceOsType, String currentAppUserDeiceOsVersion, PlatformType currentAppUserPlatformType, String currentAppUserPlatformVersion, NetworkType currentAppUserNetworkType, String currentAppUserLocale) {
        this.appUserId = appUserId;
        this.currentAppUserDeviceMacAddress = currentAppUserDeviceMacAddress;
        this.currentAppUserDeviceFcmToken = currentAppUserDeviceFcmToken;
        this.currentAppUserDeviceOsType = currentAppUserDeviceOsType;
        this.currentAppUserDeiceOsVersion = currentAppUserDeiceOsVersion;
        this.currentAppUserPlatformType = currentAppUserPlatformType;
        this.currentAppUserPlatformVersion = currentAppUserPlatformVersion;
        this.currentAppUserNetworkType = currentAppUserNetworkType;
        this.currentAppUserLocale = currentAppUserLocale;

        this.validateSelf();
    }
}
