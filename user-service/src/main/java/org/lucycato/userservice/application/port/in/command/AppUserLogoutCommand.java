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
public class AppUserLogoutCommand extends SelfValidating<AppUserLogoutCommand> {
    @NotNull
    private Long appUserId;

    public AppUserLogoutCommand(Long appUserId) {
        this.appUserId = appUserId;

        this.validateSelf();
    }
}
