package org.lucycato.notificationcommandservice.application.port.in;

import org.lucycato.notificationcommandservice.application.port.in.command.NotificationToSpecificDeviceByTargetTokenCommand;
import org.lucycato.notificationcommandservice.application.port.in.command.NotificationToSpecificDeviceByUserIdCommand;

import java.io.IOException;

public interface FCMUseCase {
    // 특정 기기에 메시지 전송
    String sendNotificationToSpecificDevice(NotificationToSpecificDeviceByUserIdCommand command);

    String sendMessageToSpecificDevice(NotificationToSpecificDeviceByTargetTokenCommand command) throws IOException;

    // 여러 기기에 메시지 전송
    // void sendNotificationToMultiDevices(MultiNotificationCommand command);

    // 특정 주제로 메시지 전송
    // void sendNotificationByTopic(TopicNotificationCommand command);

}
