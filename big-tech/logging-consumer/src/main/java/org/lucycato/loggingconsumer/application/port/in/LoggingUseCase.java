package org.lucycato.loggingconsumer.application.port.in;

import reactor.core.publisher.Mono;

public interface LoggingUseCase {
    Mono<Void> handleLogging(HandleLoggingCommand command);
}
