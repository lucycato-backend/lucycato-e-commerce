package org.lucycato.common.resolver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.common.security.AppUserRole;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppUserHeaderDetail extends SelfValidating<AppUserHeaderDetail> {
    @NotNull
    private Long appMemberId;

    @NotBlank
    private List<AppUserRole> appUserRoles;

    public AppUserHeaderDetail(
            Long appMemberId,
            List<AppUserRole> appUserRoles
    ) {
        this.appMemberId = appMemberId;
        this.appUserRoles = appUserRoles;

        this.validateSelf();
    }
}