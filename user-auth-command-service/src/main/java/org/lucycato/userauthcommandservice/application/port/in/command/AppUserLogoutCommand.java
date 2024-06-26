package org.lucycato.userauthcommandservice.application.port.in.command;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.userauthcommandservice.domain.enums.PlatformType;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppUserLogoutCommand extends SelfValidating<AppUserLogoutCommand> {
    @NotNull
    private Long appUserId;

    @NotBlank
    private String currentAppUserDeviceMacAddress;

    @NotNull
    private PlatformType currentAppUserPlatformType;

    public AppUserLogoutCommand(Long appUserId, String currentAppUserDeviceMacAddress, PlatformType currentAppUserPlatformType) {
        this.appUserId = appUserId;
        this.currentAppUserDeviceMacAddress = currentAppUserDeviceMacAddress;
        this.currentAppUserPlatformType = currentAppUserPlatformType;

        this.validateSelf();
    }
}
