package org.lucycato.gatewayservice.application.service

import org.lucycato.gatewayservice.application.port.`in`.LoggingGlobalFilterUseCase
import org.lucycato.gatewayservice.application.port.`in`.commnad.LogRequestCommand
import org.lucycato.gatewayservice.application.port.`in`.commnad.LogResponseCommand
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class LoggingGlobalFilterService : LoggingGlobalFilterUseCase {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun logRequest(command: LogRequestCommand): Mono<Void> {
        return Mono.fromRunnable {
            val originalUri = if (command.uris.isEmpty()) "Unknown" else command.uris.iterator().next().toString()
            val routeUri = "lb://${command.routeServiceId}${command.routeUriPath}"

            val headers = StringBuilder().apply {
                command.headers.forEach { (headerName, headerValues) ->
                    append("   - ")
                    append(headerName)
                    append(": ")
                    append(headerValues.joinToString(", "))
                    append("\n")
                }
            }

            log.info("\n>> -- >> -- >> -- >> REQUEST  >> -- >> -- >> -- >>\n"
                    + "Original URL: ${originalUri}\n"
                    + "Route URL: ${routeUri}\n"
                    + "Jwt: ${command.token}\n"
                    + "Method: ${command.method}\n"
                    + "Headers:\n${headers}\n"
                    + ">> -- >> -- >> -- >> REQUEST  >> -- >> -- >> -- >>\n"
            )
        }
    }

    override fun logResponse(command: LogResponseCommand): Mono<Void> {
        return Mono.fromRunnable {
            val headers = StringBuilder().apply {
                command.headers.forEach { (headerName, headerValues) ->
                    append("   - ")
                    append(headerName)
                    append(": ")
                    append(headerValues.joinToString(", "))
                    append("\n")
                }
            }

            log.info("\n<< -- << -- << -- << RESPONSE << -- << -- << -- <<\n"
                    + "Status: ${command.statusCode}\n"
                    + "Headers:\n${headers}\n"
                    + "<< -- << -- << -- << RESPONSE << -- << -- << -- <<\n",
            )
        }
    }
}