package org.lucycato.userservice.application.port.out;

import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;

public interface AdminUserPort {
    AdminUserResult registerAdminUser(
            String nickName,
            String name,
            String email,
            String password,
            String phoneNumber
    );

    AdminUserResult getAdminUserResult(
            String email,
            String password
    );

    AdminUserResult addAdminUserRole(Long adminUserId, AdminUserRole targetAdminUserRole);

    AdminUserResult removeAdminUserRole(Long adminUserId, AdminUserRole targetAdminUserRole);
}
