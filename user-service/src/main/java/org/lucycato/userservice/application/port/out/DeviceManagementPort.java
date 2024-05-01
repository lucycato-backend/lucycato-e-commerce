package org.lucycato.userservice.application.port.out;

import org.lucycato.userservice.domain.enums.PlatformType;
import org.lucycato.userservice.domain.enums.DeviceOsType;
import org.lucycato.userservice.domain.enums.NetworkType;

public interface DeviceManagementPort {
    void modifyAdminUserDeviceInfo(
            Long adminUserId,
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion,
            PlatformType platformType,
            String platformVersion,
            NetworkType networkType,
            String locale
    );

    void modifyAppUserDeviceInfo(
            Long appUserId,
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion,
            PlatformType platformType,
            String platformVersion,
            NetworkType networkType,
            String locale
    );

    void modifyAdminUserDeviceLogout(
            Long adminUserId,
            String deviceMacAddress,
            PlatformType platformType
    );

    void modifyAppUserDeviceLogout(
            Long appUserId,
            String deviceMacAddress,
            PlatformType platformType
    );
}
