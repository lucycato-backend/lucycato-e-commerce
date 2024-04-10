package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.adapter.in.web.response.OnBoardingResponse;
import org.lucycato.userservice.adapter.in.web.response.UserLoginResponse;
import org.lucycato.userservice.adapter.in.web.response.UserRegisterResponse;
import org.lucycato.userservice.application.port.in.command.AppLogoutCommand;
import org.lucycato.userservice.application.port.in.command.UserLoginCommand;
import org.lucycato.userservice.application.port.in.command.UserRegisterCommand;

import java.util.List;

public interface UserUseCase {
    List<OnBoardingResponse> getOnBoardingList();

    UserRegisterResponse register(UserRegisterCommand command);

    UserLoginResponse login(UserLoginCommand command);

    Void logout(AppLogoutCommand command);
}
