package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.security.AdminUserRole;

@Getter
@NoArgsConstructor
public class ModifyAdminUserRoleRequest {
    private Long targetUserId;

    private AdminUserRole role;
}
