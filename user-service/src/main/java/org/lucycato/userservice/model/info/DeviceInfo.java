package org.lucycato.userservice.model.info;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.userservice.model.enums.DeviceOsType;

@Getter
@Setter
@NoArgsConstructor
public class DeviceInfo {
    private String deviceManAddress;

    private String deviceFcmToken;

    private DeviceOsType deviceOsType;

    private String deviceOsVersion;

    public DeviceInfo(String deviceManAddress, String deviceFcmToken, DeviceOsType deviceOsType, String deviceOsVersion) {
        this.deviceManAddress = deviceManAddress;
        this.deviceFcmToken = deviceFcmToken;
        this.deviceOsType = deviceOsType;
        this.deviceOsVersion = deviceOsVersion;
    }
}
