package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.application.port.in.command.AppLogoutCommand;
import org.lucycato.userservice.application.port.in.command.UserLoginCommand;
import org.lucycato.userservice.application.port.in.command.UserRegisterCommand;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AdminUserLogin;

public interface UserUseCase {

    AdminUser register(UserRegisterCommand command);

    AdminUserLogin login(UserLoginCommand command);

    Void logout(AppLogoutCommand command);
}
