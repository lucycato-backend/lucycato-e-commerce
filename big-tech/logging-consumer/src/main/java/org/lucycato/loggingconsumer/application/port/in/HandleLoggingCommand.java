package org.lucycato.loggingconsumer.application.port.in;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HandleLoggingCommand {
    private String loggingKey;

    private String loggingValue;
}
