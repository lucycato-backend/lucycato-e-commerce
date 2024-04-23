package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.UserLogin;

public interface AdminUserUseCase {

    AdminUser register(AdminUserRegisterCommand command);

    UserLogin login(AdminUserLoginCommand command);

    void logout(AdminUserLogoutCommand command);

    AdminUser modifyAdminUserRole(ModifyAdminUserRoleCommand command);
}
