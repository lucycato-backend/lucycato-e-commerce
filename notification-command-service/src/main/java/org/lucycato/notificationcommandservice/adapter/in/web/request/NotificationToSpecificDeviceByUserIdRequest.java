package org.lucycato.notificationcommandservice.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationToSpecificDeviceByUserIdRequest {

    private Long targetUserId;
    private String body;
    private String title;
}
