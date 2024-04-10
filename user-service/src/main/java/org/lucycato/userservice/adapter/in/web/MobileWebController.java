package org.lucycato.userservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.api.Api;
import org.lucycato.userservice.adapter.in.web.response.AppVersionCheckResponse;
import org.lucycato.userservice.application.port.in.MobileWebUseCase;
import org.lucycato.userservice.application.port.in.command.AppVersionCheckCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RequestMapping
@RestController
@RequiredArgsConstructor
public class MobileWebController {
    private final MobileWebUseCase mobileWebUseCase;

    @GetMapping("/open-api/lucycato/v1/mobile/app-version-check")
    public Api<AppVersionCheckResponse> appVersionCheck(@RequestParam String currentAppVersion) {
        AppVersionCheckCommand command = new AppVersionCheckCommand(currentAppVersion);
        return Api.OK(mobileWebUseCase.appVersionCheck(command));
    }
}
