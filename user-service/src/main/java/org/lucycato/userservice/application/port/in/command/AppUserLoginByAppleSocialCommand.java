package org.lucycato.userservice.application.port.in.command;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.userservice.model.enums.AppOrBrowserType;
import org.lucycato.userservice.model.enums.DeviceOsType;
import org.lucycato.userservice.model.enums.NetworkType;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppUserLoginByAppleSocialCommand extends SelfValidating<AppUserLoginByAppleSocialCommand> {
    @NotBlank
    private String deviceMacAddress;

    @NotBlank
    private String deviceFcmToken;

    @NotNull
    private DeviceOsType deviceOsType;

    @NotBlank
    private String deiceOsVersion;

    @NotNull
    private AppOrBrowserType appOrBrowserType;

    @NotBlank
    private String appOrBrowserVersion;

    @NotNull
    private NetworkType networkType;

    @NotBlank
    private String locale;

    public AppUserLoginByAppleSocialCommand(String deviceMacAddress, String deviceFcmToken, DeviceOsType deviceOsType, String deiceOsVersion, AppOrBrowserType appOrBrowserType, String appOrBrowserVersion, NetworkType networkType, String locale) {
        this.deviceMacAddress = deviceMacAddress;
        this.deviceFcmToken = deviceFcmToken;
        this.deviceOsType = deviceOsType;
        this.deiceOsVersion = deiceOsVersion;
        this.appOrBrowserType = appOrBrowserType;
        this.appOrBrowserVersion = appOrBrowserVersion;
        this.networkType = networkType;
        this.locale = locale;

        this.validateSelf();
    }
}
