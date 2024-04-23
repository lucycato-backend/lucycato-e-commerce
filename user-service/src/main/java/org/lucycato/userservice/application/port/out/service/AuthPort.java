package org.lucycato.userservice.application.port.out.service;

import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.application.port.out.result.IssueFcmTokenResult;

import java.util.List;

public interface AuthPort {
    IssueFcmTokenResult issueAdminUserFcmToken(Long adminUserId, List<AdminUserRole> adminUserRoles);
}
