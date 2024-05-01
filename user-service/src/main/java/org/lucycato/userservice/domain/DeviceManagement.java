package org.lucycato.userservice.domain;


import lombok.*;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.domain.enums.DeviceOsType;

import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeviceManagement {
    private String deviceManAddress;

    private String deviceFcmToken;

    private DeviceOsType deviceOsType;

    private String deviceOsVersion;

    private List<Platform> platforms;

    public static DeviceManagement create(
            String deviceManAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion,
            List<Platform> platforms
    ) {
        return DeviceManagement.builder()
                .deviceManAddress(deviceManAddress)
                .deviceFcmToken(deviceFcmToken)
                .deviceOsType(deviceOsType)
                .deviceOsVersion(deviceOsVersion)
                .platforms(platforms)
                .build();
    }
}
