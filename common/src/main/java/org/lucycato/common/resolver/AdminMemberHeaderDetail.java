package org.lucycato.common.resolver;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminMemberHeaderDetail extends SelfValidating<AdminMemberHeaderDetail> {
    @NotNull
    private Long adminMemberId;

    public AdminMemberHeaderDetail(Long adminMemberId) {
        this.adminMemberId = adminMemberId;

        this.validateSelf();
    }
}
