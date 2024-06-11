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

            log.info("""
            
            >> -- >> -- >> -- >> REQUEST  >> -- >> -- >> -- >>
            Original URL: $originalUri
            Route URL: $routeUri
            Jwt: ${command.token}
            Method: ${command.method}
            Headers:
            $headers
            >> -- >> -- >> -- >> REQUEST  >> -- >> -- >> -- >>
            
            """.trimIndent()
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

            log.info("""
                
            << -- << -- << -- << RESPONSE << -- << -- << -- <<
            Status: ${command.statusCode}
            Headers:
            $headers
            << -- << -- << -- << RESPONSE << -- << -- << -- <<
            
            """.trimIndent()
            )
        }
    }
}