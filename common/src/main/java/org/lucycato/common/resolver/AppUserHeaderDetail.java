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

    public AppUserHeaderDetail(Long appUserId) {
        this.appUserId = appUserId;

        this.validateSelf();
    }
}
