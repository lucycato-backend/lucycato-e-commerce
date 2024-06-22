package org.lucycato.common.resolver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.lucycato.common.SelfValidating;
import org.lucycato.common.security.AdminUserRole;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminUserHeaderDetail extends SelfValidating<AdminUserHeaderDetail> {
    @NotNull
    private Long adminUserId;

    public AdminUserHeaderDetail(Long adminUserId) {
        this.adminUserId = adminUserId;
    }
}
