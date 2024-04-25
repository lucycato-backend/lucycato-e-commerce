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
public class AdminUserLogoutCommand extends SelfValidating<AdminUserLogoutCommand> {
    @NotNull
    private Long adminMemberId;

    @NotBlank
    private String deviceMacAddress;

    @NotNull
    private AppOrBrowserType appOrBrowserType;

    public AdminUserLogoutCommand(Long adminMemberId, String deviceMacAddress, AppOrBrowserType appOrBrowserType) {
        this.adminMemberId = adminMemberId;
        this.deviceMacAddress = deviceMacAddress;
        this.appOrBrowserType = appOrBrowserType;

        this.validateSelf();
    }
}
