package org.lucycato.notificationcommandservice.adapter.out.externalsystem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ExternalSystemAdapter;
import org.lucycato.notificationcommandservice.application.port.out.FCMPort;

@ExternalSystemAdapter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FCMAdapter implements FCMPort {
    private boolean validate_only;
    private Message message;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private Notification notification;
        private String token;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }
}