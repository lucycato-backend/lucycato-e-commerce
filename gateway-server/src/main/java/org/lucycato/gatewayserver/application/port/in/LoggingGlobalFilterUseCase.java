package org.lucycato.gatewayserver.application.port.in;

import org.lucycato.gatewayserver.application.port.in.command.LogRequestCommand;
import org.lucycato.gatewayserver.application.port.in.command.LogResponseCommand;
import reactor.core.publisher.Mono;

public interface LoggingGlobalFilterUseCase {
    Mono<Void> logRequest(LogRequestCommand command);

    Mono<Void> logResponse(LogResponseCommand command);
}
