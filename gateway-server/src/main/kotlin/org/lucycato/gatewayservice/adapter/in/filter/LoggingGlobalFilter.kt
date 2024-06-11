package org.lucycato.gatewayservice.adapter.`in`.filter

import org.lucycato.common.error.ErrorCodeImpl
import org.lucycato.common.exception.ApiExceptionImpl
import org.lucycato.gatewayservice.application.port.`in`.LoggingGlobalFilterUseCase
import org.lucycato.gatewayservice.application.port.`in`.commnad.LogRequestCommand
import org.lucycato.gatewayservice.application.port.`in`.commnad.LogResponseCommand
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.net.URI

@Order(Int.MIN_VALUE)
@Component
class LoggingGlobalFilter(
        private val loggingGlobalFilterUseCase: LoggingGlobalFilterUseCase
) : AbstractGatewayFilterFactory<LoggingGlobalFilter.Config>(Config::class.java) {

    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            logRequest(exchange)
                    .subscribeOn(Schedulers.parallel())
                    .subscribe()
            chain.filter(exchange).then(logResponse(exchange.response))
        }
    }

    fun logRequest(exchange: ServerWebExchange): Mono<Void> {
        val request = exchange.request
        val uris: Set<URI> = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, emptySet())
        val route: Route? = exchange.getAttribute(GATEWAY_ROUTE_ATTR)
        val routeUri: URI? = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)
        val httpHeaders = request.headers
        val map = httpHeaders.toMap()
        val token = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION)

        val command = LogRequestCommand(
                uris,
                route?.id,
                routeUri?.path,
                request.method.toString(),
                map,
                token
        )

        return loggingGlobalFilterUseCase.logRequest(command)
    }

    private fun logResponse(response: ServerHttpResponse): Mono<Void> {
        val httpHeaders: HttpHeaders = response.headers
        val map = httpHeaders.mapValues { it.value }

        val statusCode = response.statusCode?.value() ?: throw ApiExceptionImpl(ErrorCodeImpl.VALIDATION)

        val command = LogResponseCommand(
                statusCode,
                map
        )

        return loggingGlobalFilterUseCase.logResponse(command)
    }

    data class Config(var message: String)
}