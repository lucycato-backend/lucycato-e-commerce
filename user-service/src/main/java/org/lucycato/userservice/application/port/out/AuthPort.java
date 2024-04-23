package org.lucycato.userservice.application.port.out;

import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.application.port.out.result.IssueFcmTokenResult;

import java.util.List;

public interface AuthPort {
    Boolean verifyPhoneNumberAuthCode(String phoneNumberAuthCode);

    IssueFcmTokenResult issueAdminUserFcmToken(Long adminUserId, List<AdminUserRole> adminUserRoles);
}
