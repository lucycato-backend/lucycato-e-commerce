package org.lucycato.userauthcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.lucycato.userauthcommandservice.application.port.in.QueryAppUserUseCase;
import org.lucycato.userauthcommandservice.application.port.in.command.GetAppUserCommand;
import org.lucycato.userauthcommandservice.application.port.in.command.GetAppUserDeviceManagementCommand;
import org.lucycato.userauthcommandservice.domain.AppUser;
import org.lucycato.userauthcommandservice.domain.DeviceManagement;
import org.lucycato.userauthcommandservice.domain.enums.PlatformType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class QueryAppUserController {
    private final QueryAppUserUseCase queryAppUserUseCase;

    @GetMapping("api/lucycato/v1/app/user/me")
    public AppUser getAppUser(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail
    ) {
        GetAppUserCommand command = new GetAppUserCommand(
                appUserHeaderDetail.getAppUserId(),
                appUserHeaderDetail.getCurrentAppUserDeviceMacAddress(),
                PlatformType.valueOf(appUserHeaderDetail.getCurrentAppUserPlatFormType())
        );
        return queryAppUserUseCase.getAppUser(command);
    }

    @GetMapping("api/lucycato/v1/app/user/device-management/me")
    public List<DeviceManagement> getAppUserDeviceManagementList(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail
    ) {
        GetAppUserDeviceManagementCommand command = new GetAppUserDeviceManagementCommand(
                appUserHeaderDetail.getAppUserId()
        );
        return queryAppUserUseCase.getAppUserDeviceManagementList(command);
    }
}
