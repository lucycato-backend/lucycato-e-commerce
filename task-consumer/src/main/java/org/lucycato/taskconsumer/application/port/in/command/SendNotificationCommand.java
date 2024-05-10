package org.lucycato.taskconsumer.application.port.in.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SendNotificationCommand extends SelfValidating<SendNotificationCommand> {
    private String fcmToken;

    private String title;

    private String content;

    public SendNotificationCommand(String fcmToken, String title, String content) {
        this.fcmToken = fcmToken;
        this.title = title;
        this.content = content;

        this.validateSelf();
    }
}
