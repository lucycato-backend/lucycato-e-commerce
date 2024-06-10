package org.lucycato.userauthcommandservice.domain;


import lombok.*;
import org.lucycato.userauthcommandservice.domain.enums.DeviceOsType;

import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeviceManagement {
    private String deviceMacAddress;

    private String deviceFcmToken;

    private DeviceOsType deviceOsType;

    private String deviceOsVersion;

    private List<Platform> platforms;

    public static DeviceManagement create(
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion,
            List<Platform> platforms
    ) {
        return DeviceManagement.builder()
                .deviceMacAddress(deviceMacAddress)
                .deviceFcmToken(deviceFcmToken)
                .deviceOsType(deviceOsType)
                .deviceOsVersion(deviceOsVersion)
                .platforms(platforms)
                .build();
    }
}
