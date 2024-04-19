package org.lucycato.userservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.api.Api;
import org.lucycato.userservice.adapter.in.web.request.UserRegisterRequest;
import org.lucycato.userservice.application.port.in.UserUseCase;
import org.lucycato.userservice.application.port.in.command.UserLoginCommand;
import org.lucycato.userservice.application.port.in.command.UserRegisterCommand;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AdminUserLogin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase userUseCase;

    @PostMapping("open-api/lucycato/v1/user/register")
    public Api<AdminUser> register(@RequestBody UserRegisterRequest request) {
        UserRegisterCommand command = new UserRegisterCommand(
                request.getNickName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber()
        );
        return Api.OK(userUseCase.register(command));
    }

    @PostMapping("open-api/lucycato/v1/user/login")
    public Api<AdminUserLogin> login(@RequestBody UserRegisterRequest request) {
        UserLoginCommand command = new UserLoginCommand(
                request.getEmail(),
                request.getPassword(),
                request.getDeviceMacAddress(),
                request.getDeviceFcmToken(),
                request.getDeviceOsType(),
                request.getDeiceOsVersion()
        );
        return Api.OK(userUseCase.login(command));
    }
}
