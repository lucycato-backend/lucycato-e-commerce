package org.lucycato.userservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.userservice.domain.enums.PlatformType;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminUserLogoutCommand extends SelfValidating<AdminUserLogoutCommand> {
    @NotNull
    private Long adminUserId;

    @NotBlank
    private String currentAdminUserDeviceMacAddress;

    @NotNull
    private PlatformType currentAdminUserPlatformType;

    public AdminUserLogoutCommand(Long adminUserId, String currentAdminUserDeviceMacAddress, PlatformType platformType) {
        this.adminUserId = adminUserId;
        this.currentAdminUserDeviceMacAddress = currentAdminUserDeviceMacAddress;
        this.currentAdminUserPlatformType = platformType;

        this.validateSelf();
    }
}
