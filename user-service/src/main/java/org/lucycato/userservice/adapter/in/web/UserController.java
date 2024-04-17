package org.lucycato.userservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AppMemberHeaders;
import org.lucycato.common.api.Api;
import org.lucycato.common.resolver.AppMemberHeaderDetail;
import org.lucycato.userservice.adapter.in.web.request.UserRegisterRequest;
import org.lucycato.userservice.adapter.in.web.response.OnBoardingResponse;
import org.lucycato.userservice.adapter.in.web.response.UserLoginResponse;
import org.lucycato.userservice.adapter.in.web.response.UserRegisterResponse;
import org.lucycato.userservice.application.port.in.UserUseCase;
import org.lucycato.userservice.application.port.in.command.AppLogoutCommand;
import org.lucycato.userservice.application.port.in.command.UserLoginCommand;
import org.lucycato.userservice.application.port.in.command.UserRegisterCommand;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    // on-board
    @GetMapping("open-api/lucycato/v1/mobile/on-boarding/list")
    public Api<List<OnBoardingResponse>> getOnboardingList() {
        return Api.OK(userUseCase.getOnBoardingList());
    }

    @PostMapping("open-api/lucycato/v1/user/register")
    public Api<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {
        UserRegisterCommand command = new UserRegisterCommand(
                request.getNickName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber()
        );
        return Api.OK(userUseCase.register(command));
    }

    @PostMapping("open-api/lucycato/v1/user/login")
    public Api<UserLoginResponse> login(@RequestBody UserRegisterRequest request) {
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

    @PostMapping("api/lucycato/v1/user/logout")
    public Api<Void> logout(@AppMemberHeaders AppMemberHeaderDetail appMemberHeaderDetail) {
        AppLogoutCommand command = new AppLogoutCommand(
                appMemberHeaderDetail.getAppMemberId()
        );
        return Api.OK(userUseCase.logout(command));
    }


    // logout
    // me (auto login)
    // 권한 위임
    // 일반 사용자의 membership 관리

}
