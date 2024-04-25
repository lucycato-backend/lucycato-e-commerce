package org.lucycato.userservice.model.info;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.userservice.model.enums.DeviceOsType;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeviceInfo {
    private String deviceManAddress;

    private String deviceFcmToken;

    private DeviceOsType deviceOsType;

    private String deviceOsVersion;

    private List<AppOrBrowserInfo> appOrBrowserInfos;

    public DeviceInfo(String deviceManAddress, String deviceFcmToken, DeviceOsType deviceOsType, String deviceOsVersion, List<AppOrBrowserInfo> appOrBrowserInfos) {
        this.deviceManAddress = deviceManAddress;
        this.deviceFcmToken = deviceFcmToken;
        this.deviceOsType = deviceOsType;
        this.deviceOsVersion = deviceOsVersion;
        this.appOrBrowserInfos = appOrBrowserInfos;
    }
}
