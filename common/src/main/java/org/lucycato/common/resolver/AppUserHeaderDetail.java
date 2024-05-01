package org.lucycato.common.resolver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppUserHeaderDetail extends SelfValidating<AppUserHeaderDetail> {
    @NotNull
    private Long appUserId;

    @NotBlank
    private String currentAppUserDeviceMacAddress;

    @NotBlank
    private String currentAppUserPlatFormType;

    public AppUserHeaderDetail(Long appUserId, String currentAppUserDeviceMacAddress, String currentAppUserPlatFormType) {
        this.appUserId = appUserId;
        this.currentAppUserDeviceMacAddress = currentAppUserDeviceMacAddress;
        this.currentAppUserPlatFormType = currentAppUserPlatFormType;

        this.validateSelf();
    }
}
