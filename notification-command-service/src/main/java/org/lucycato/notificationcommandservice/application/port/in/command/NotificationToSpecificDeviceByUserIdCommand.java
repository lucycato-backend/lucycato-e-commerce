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
public class NotificationToSpecificDeviceByUserIdCommand extends SelfValidating<NotificationToSpecificDeviceByUserIdCommand> {

    private Long targetUserId;
    private String body;
    private String title;

    public NotificationToSpecificDeviceByUserIdCommand(Long targetUserId, String body, String title) {
        this.targetUserId = targetUserId;
        this.body = body;
        this.title = title;

        this.validateSelf();
    }
}
