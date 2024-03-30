package org.lucycato.userservice.application.service;

import com.fasterxml.jackson.core.JsonPointer;
import lombok.RequiredArgsConstructor;
import org.lucycato.userservice.adapter.in.web.response.AppVersionCheckResponse;
import org.lucycato.userservice.application.port.in.MobileWebUseCase;
import org.lucycato.userservice.application.port.in.command.AppVersionCheckCommand;
import org.lucycato.userservice.application.port.out.AppVersionPort;
import org.lucycato.userservice.application.port.out.response.AppVersionResponse;
import org.lucycato.userservice.model.enums.AppVersionCheckStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MobileWebService implements MobileWebUseCase {
    private final AppVersionPort appVersionPort;
    @Override
    public AppVersionCheckResponse appVersionCheck(AppVersionCheckCommand command) {
        String[] splitCurrentAppVersion = command.getAppVersion().split("\\."); // 1.10.3
        Integer majorCurrentVersion = Integer.parseInt(splitCurrentAppVersion[0]);
        Integer minorCurrentVersion = Integer.parseInt(splitCurrentAppVersion[1]);

        AppVersionResponse appVersionResponse = appVersionPort.getStandardAppVersion();
        String[] splitStandardAppVersion = appVersionResponse.getStandardAppVersion().split("\\."); //1.11.0
        Integer majorStandardVersion = Integer.parseInt(splitStandardAppVersion[0]);
        Integer minorStandardVersion = Integer.parseInt(splitStandardAppVersion[1]);

        AppVersionCheckResponse appVersionCheckResponse;
        if (majorStandardVersion - majorCurrentVersion > 0) {
            appVersionCheckResponse = AppVersionCheckResponse.builder()
                    .appVersionCheckStatus(AppVersionCheckStatus.FORCE)
                    .build();
        } else if (minorStandardVersion - minorCurrentVersion > 0) {
            appVersionCheckResponse = AppVersionCheckResponse.builder()
                    .appVersionCheckStatus(AppVersionCheckStatus.REQUIRE)
                    .build();
        } else {
            appVersionCheckResponse = AppVersionCheckResponse.builder()
                    .appVersionCheckStatus(AppVersionCheckStatus.PASS)
                    .build();
        }
        return appVersionCheckResponse;
    }
}
