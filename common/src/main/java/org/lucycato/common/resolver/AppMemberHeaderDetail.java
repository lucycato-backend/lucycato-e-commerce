package org.lucycato.common.resolver;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppMemberHeaderDetail extends SelfValidating<AppMemberHeaderDetail> {
    @NotNull
    private Long appMemberId;

    public AppMemberHeaderDetail(Long appMemberId) {
        this.appMemberId = appMemberId;

        this.validateSelf();
    }
}
