package org.lucycato.notificationcommandservice.adapter.in.web.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationToSpecificDeviceByTargetTokenRequest {

    private String targetToken;
    private String body;
    private String title;
}
