package org.lucycato.userauthcommandservice.application.port.out;

import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userauthcommandservice.application.port.out.result.AdminUserResult;
import org.lucycato.userauthcommandservice.application.port.out.result.AdminfindPasswordResult;

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

    AdminUserResult getAdminUserResult(String email);

    AdminUserResult addAdminUserRole(Long adminUserId, AdminUserRole targetAdminUserRole);

    AdminUserResult removeAdminUserRole(Long adminUserId, AdminUserRole targetAdminUserRole);

    void setTempPasswordToRedis(String tempPassword, String email);

    AdminfindPasswordResult getTempPasswordToRedis(String email);

    void sendTempPasswordByEmail(String tempPassword, String email);
}
