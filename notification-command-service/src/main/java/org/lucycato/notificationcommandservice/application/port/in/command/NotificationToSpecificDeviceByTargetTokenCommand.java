package org.lucycato.notificationcommandservice.application.port.in.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Builder
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NotificationToSpecificDeviceByTargetTokenCommand extends SelfValidating<NotificationToSpecificDeviceByTargetTokenCommand> {

    private String targetToken;
    private String body;
    private String title;

    public NotificationToSpecificDeviceByTargetTokenCommand(String targetToken, String body, String title) {
        this.targetToken = targetToken;
        this.body = body;
        this.title = title;

        this.validateSelf();
    }
}
