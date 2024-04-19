package org.lucycato.common.resolver;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminUserHeaderDetail extends SelfValidating<AdminUserHeaderDetail> {
    @NotNull
    private Long adminMemberId;

    public AdminUserHeaderDetail(Long adminMemberId) {
        this.adminMemberId = adminMemberId;

        this.validateSelf();
    }
}
