package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.userservice.domain.enums.PlatformType;
import org.lucycato.userservice.domain.enums.DeviceOsType;
import org.lucycato.userservice.domain.enums.NetworkType;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminUserLoginCheckCommand extends SelfValidating<AdminUserLoginCheckCommand> {
    @NotNull
    private Long adminUserId;

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

    public AdminUserLoginCheckCommand(Long adminUserId, String currentAdminUserDeviceMacAddress, String currentAdminUserDeviceFcmToken, DeviceOsType currentAdminUserDeviceOsType, String currentAdminUserDeiceOsVersion, PlatformType currentAdminUserPlatformType, String currentAdminUserPlatformVersion, NetworkType currentAdminUserNetworkType, String currentAdminUserLocale) {
        this.adminUserId = adminUserId;
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
