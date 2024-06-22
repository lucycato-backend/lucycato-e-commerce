package org.lucycato.gatewayservice.adapter.`in`.filter

import org.lucycato.common.context.XHeaderContext
import org.lucycato.common.exception.ApiExceptionImpl
import org.lucycato.gatewayservice.application.port.`in`.AuthGlobalFilterUseCase
import org.lucycato.gatewayservice.error.GatewayErrorCodeImpl
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Order(-10)
@Component
class AuthGlobalFilter(
        private val authGlobalFilterUseCase: AuthGlobalFilterUseCase
) : AbstractGatewayFilterFactory<AuthGlobalFilter.Config>(Config::class.java) {
    companion object {
        const val WHITELIST_OPEN_API_KEY = "open-api"
        const val WHITELIST_API_KEY = "api"
        const val WHITELIST_SWAGGER_KEY = "api-docs"
        const val AUTH_ADMIN_KEY = "admin"
        const val AUTH_APP_KEY = "app"
    }

    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            Mono.just(exchange.request.uri.path)
                    .map { uri ->
                        uri.substring(1).split("/")
                    }
                    .flatMap { split ->
                        val whitelist = split[1]
                        when (whitelist) {
                            WHITELIST_OPEN_API_KEY -> Mono.just("")
                            WHITELIST_SWAGGER_KEY -> Mono.just("")
                            WHITELIST_API_KEY -> {
                                val request = exchange.request
                                val auth = split[2]
                                when (auth) {
                                    AUTH_ADMIN_KEY -> {
                                        val token = request.headers.getFirst(HttpHeaders.AUTHORIZATION)
                                        authGlobalFilterUseCase.parseJwtToAdminUserJson(token)
                                    }

                                    AUTH_APP_KEY -> {
                                        val token = request.headers.getFirst(HttpHeaders.AUTHORIZATION)
                                        authGlobalFilterUseCase.parseJwtToAppUserJson(token)
                                    }

                                    else -> Mono.error(ApiExceptionImpl(GatewayErrorCodeImpl.INVALID_URI))
                                }
                            }

                            else -> Mono.error(ApiExceptionImpl(GatewayErrorCodeImpl.INVALID_URI))
                        }
                    }
                    .onErrorResume { error ->
                        chain.filter(exchange)
                                .then(Mono.error(ApiExceptionImpl(GatewayErrorCodeImpl.INVALID_URI, error)))
                    }
                    .switchIfEmpty(
                            chain.filter(exchange)
                                    .then(Mono.error(ApiExceptionImpl(GatewayErrorCodeImpl.INVALID_TOKEN)))
                    )
                    .flatMap { authJson ->
                        chain.filter(exchange.mutate().request(exchange.request.mutate().headers { httpHeaders ->
                            if (authJson.isNotEmpty()) {
                                httpHeaders.set(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, authJson)
                            }
                        }.build()).build())
                    }
        }
    }

    data class Config(var message: String)
}