package org.lucycato.notificationcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.notificationcommandservice.adapter.in.web.request.NotificationToSpecificDeviceByTargetTokenRequest;
import org.lucycato.notificationcommandservice.application.port.in.FCMUseCase;
import org.lucycato.notificationcommandservice.application.port.in.command.NotificationToSpecificDeviceByTargetTokenCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FCMController {
    private final FCMUseCase fcmUseCase;

    // 특정한 알림을 특정 기기 토큰을 통해 -> FB 서버에 찌르기 위한 API
//    @PostMapping("api/app/notification/v1/specific-device")
//    public void sendNotificationToSpecificDevice(
//            @RequestBody
//            NotificationToSpecificDeviceRequest request
//    ) {
//        NotificationToSpecificDeviceCommand command = new NotificationToSpecificDeviceCommand(
//                request.getTargetUserId(),
//                request.getBody(),
//                request.getTitle()
//        );
//        return fcmUseCase.sendNotificationToSpecificDevice(command);
//    }

    @PostMapping("api/fcm")
    public String pushMessage(
            @RequestBody
            NotificationToSpecificDeviceByTargetTokenRequest request
    ) throws IOException {
        NotificationToSpecificDeviceByTargetTokenCommand command = new NotificationToSpecificDeviceByTargetTokenCommand(
                request.getTargetToken(),
                request.getBody(),
                request.getTitle()
        );
        return fcmUseCase.sendMessageToSpecificDevice(command);
    }
}
