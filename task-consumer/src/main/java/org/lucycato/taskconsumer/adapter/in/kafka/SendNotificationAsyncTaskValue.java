package org.lucycato.taskconsumer.adapter.in.kafka;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendNotificationAsyncTaskValue {
    private String fcmToken;

    private String title;

    private String content;
}
