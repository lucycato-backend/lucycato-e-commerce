package org.lucycato.loggingconsumer.application.port.in;

import reactor.core.publisher.Mono;

public interface LoggingUseCase {
    void handleLogging(HandleLoggingCommand command);
}
