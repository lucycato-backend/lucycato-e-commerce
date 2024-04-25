package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.security.AdminUserRole;

@Getter
@NoArgsConstructor
public class ModifyAdminUserRoleRequest {
    private Long targetUserId;

    private AdminUserRole role;
}
