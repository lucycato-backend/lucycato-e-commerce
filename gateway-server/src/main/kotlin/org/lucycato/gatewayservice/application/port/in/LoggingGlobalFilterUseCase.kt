package org.lucycato.gatewayservice.application.port.`in`

import org.lucycato.gatewayservice.application.port.`in`.commnad.LogRequestCommand
import org.lucycato.gatewayservice.application.port.`in`.commnad.LogResponseCommand
import reactor.core.publisher.Mono

interface LoggingGlobalFilterUseCase {
    fun logRequest(command: LogRequestCommand): Mono<Void>

    fun logResponse(command: LogResponseCommand): Mono<Void>
}