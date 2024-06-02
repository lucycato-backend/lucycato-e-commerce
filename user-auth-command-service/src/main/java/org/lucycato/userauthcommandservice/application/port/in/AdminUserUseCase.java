package org.lucycato.userauthcommandservice.application.port.in;

import org.lucycato.userauthcommandservice.application.port.in.command.*;
import org.lucycato.userauthcommandservice.domain.AdminUser;
import org.lucycato.userauthcommandservice.domain.AdminUserLogin;

public interface AdminUserUseCase {

    AdminUserLogin register(AdminUserRegisterCommand command);

    AdminUserLogin login(AdminUserLoginCommand command);

    void loginCheck(AdminUserLoginCheckCommand command);

    void logout(AdminUserLogoutCommand command);

    AdminUser addAdminUserRole(ModifyAdminUserRoleCommand command);

    AdminUser removeAdminUserRole(ModifyAdminUserRoleCommand command);

    boolean createAdminUserTempPassword(AdminUserCreateTempPasswordCommand command);

    boolean checkAdminUserTempPassword(AdminUserCheckTempPasswordCommand command);
}
