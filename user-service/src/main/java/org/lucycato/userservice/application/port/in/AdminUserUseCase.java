package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AdminUserLogin;
import org.lucycato.userservice.domain.AdminUserProfile;
import org.lucycato.userservice.domain.AppUserLogin;

public interface AdminUserUseCase {

    AdminUserLogin register(AdminUserRegisterCommand command);

    AdminUserLogin login(AdminUserLoginCommand command);

    void loginCheck(AdminUserLoginCheckCommand command);

    void logout(AdminUserLogoutCommand command);

    AdminUser addAdminUserRole(ModifyAdminUserRoleCommand command);

    AdminUser removeAdminUserRole(ModifyAdminUserRoleCommand command);
}
