package org.lucycato.userauthcommandservice.adapter.out.persistence.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.userauthcommandservice.domain.enums.DeviceOsType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeviceVo {
    private String deviceManAddress;

    private String deviceFcmToken;

    private DeviceOsType deviceOsType;

    private String deviceOsVersion;

    private List<PlatformVo> platformVos;

    public DeviceVo(String deviceManAddress, String deviceFcmToken, DeviceOsType deviceOsType, String deviceOsVersion, List<PlatformVo> platformVos) {
        this.deviceManAddress = deviceManAddress;
        this.deviceFcmToken = deviceFcmToken;
        this.deviceOsType = deviceOsType;
        this.deviceOsVersion = deviceOsVersion;
        this.platformVos = platformVos;
    }
}
