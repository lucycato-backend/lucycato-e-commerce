package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.UserLogin;

public interface AdminUserUseCase {

    UserLogin register(AdminUserRegisterCommand command);

    UserLogin login(AdminUserLoginCommand command);

    void loginCheck(AdminUserLoginCheckCommand command);

    void logout(AdminUserLogoutCommand command);

    AdminUser addAdminUserRole(ModifyAdminUserRoleCommand command);

    AdminUser removeAdminUserRole(ModifyAdminUserRoleCommand command);
}
