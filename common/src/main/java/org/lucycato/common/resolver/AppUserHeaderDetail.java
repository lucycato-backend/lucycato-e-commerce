package org.lucycato.common.resolver;

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
    private Long appMemberId;

    public AppUserHeaderDetail(Long appMemberId) {
        this.appMemberId = appMemberId;

        this.validateSelf();
    }
}
