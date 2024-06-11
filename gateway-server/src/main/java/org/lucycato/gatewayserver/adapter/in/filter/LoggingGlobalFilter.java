package org.lucycato.gatewayserver.adapter.in.filter;

import io.netty.handler.codec.Headers;
import lombok.Data;
import org.lucycato.gatewayserver.application.port.in.LoggingGlobalFilterUseCase;
import org.lucycato.gatewayserver.application.port.in.command.LogRequestCommand;
import org.lucycato.gatewayserver.application.port.in.command.LogResponseCommand;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.util.*;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

@Order(Integer.MIN_VALUE)
@Component
public class LoggingGlobalFilter extends AbstractGatewayFilterFactory<LoggingGlobalFilter.Config> {
    private final LoggingGlobalFilterUseCase loggingGlobalFilterUseCase;

    public LoggingGlobalFilter(LoggingGlobalFilterUseCase loggingGlobalFilterUseCase) {
        super(Config.class);
        this.loggingGlobalFilterUseCase = loggingGlobalFilterUseCase;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            logRequest(exchange)
                    .subscribeOn(Schedulers.parallel())
                    .subscribe();
            return chain.filter(exchange).then(logResponse(exchange.getResponse()));
        };
    }

    private Mono<Void> logRequest(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        Set<URI> uris = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet());
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        URI routeUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        Map<String, List<String>> map = new HashMap<>();
        for (String key : httpHeaders.keySet()) {
            List<String> values = httpHeaders.get(key);
            map.put(key, values);
        }
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        LogRequestCommand command = new LogRequestCommand(
                uris,
                route.getId(),
                routeUri.getPath(),
                request.getMethod().toString(),
                map,
                token
        );

        return loggingGlobalFilterUseCase.logRequest(command);
    }

    private Mono<Void> logResponse(ServerHttpResponse response) {
        HttpHeaders httpHeaders = response.getHeaders();
        Map<String, List<String>> map = new HashMap<>();
        for (String key : httpHeaders.keySet()) {
            List<String> values = httpHeaders.get(key);
            map.put(key, values);
        }

        LogResponseCommand command = new LogResponseCommand(
                Objects.requireNonNull(response.getStatusCode()).value(),
                map
        );
        return loggingGlobalFilterUseCase.logResponse(command);
    }

    @Data
    public static class Config {
        private String message;
    }
}
