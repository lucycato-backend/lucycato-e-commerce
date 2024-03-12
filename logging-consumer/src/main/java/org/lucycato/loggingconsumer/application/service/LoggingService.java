package org.lucycato.loggingconsumer.application.service;

import org.lucycato.common.annotation.in.UseCase;
import org.lucycato.loggingconsumer.application.port.in.HandleLoggingCommand;
import org.lucycato.loggingconsumer.application.port.in.LoggingUseCase;
import reactor.core.publisher.Mono;

@UseCase
public class LoggingService implements LoggingUseCase {

    @Override
    public Mono<Void> handleLogging(HandleLoggingCommand command) {
        System.out.println("[Logging Consumer] Received logging message -> " + " < " + command.getLoggingKey() + " > " + command.getLoggingValue());
        return Mono.empty();
    }
}