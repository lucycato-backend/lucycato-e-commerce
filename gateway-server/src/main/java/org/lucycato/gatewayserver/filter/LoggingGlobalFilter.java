package org.lucycato.gatewayserver.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

@Order(Integer.MAX_VALUE)
@Slf4j
@Component
public class LoggingGlobalFilter extends AbstractGatewayFilterFactory<LoggingGlobalFilter.Config> {
    public LoggingGlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            logRequest(exchange);

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logResponse(exchange.getResponse());
            }));
        };
    }

    private void logRequest(ServerWebExchange exchange) {
        StringBuilder headers = new StringBuilder();
        ServerHttpRequest request = exchange.getRequest();
        Set<URI> uris = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet());
        String originalUri = (uris.isEmpty()) ? "Unknown" : uris.iterator().next().toString();

        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        URI routeUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        String exchangeRouteUri = "lb://" + Objects.requireNonNull(route).getId() + Objects.requireNonNull(routeUri).getPath();



        HttpHeaders httpHeaders = request.getHeaders();
        httpHeaders.forEach((headerName, headerValues) -> {
            headers
                    .append("   - ")
                    .append(headerName)
                    .append(": ")
                    .append(String.join(", ", headerValues))
                    .append("\n");
        });

        log.info("\n>> -- >> -- >> -- >> REQUEST  >> -- >> -- >> -- >>\n"
                        + "Original URL: {}\n"
                        + "Route URL: {}\n"
                        + "Method: {}\n"
                        + "Headers:\n{}\n"
                        + ">> -- >> -- >> -- >> REQUEST  >> -- >> -- >> -- >>\n",
                originalUri,
                exchangeRouteUri,
                request.getMethod(),
                headers
        );
    }

    private void logResponse(ServerHttpResponse response) {
        StringBuilder headers = new StringBuilder();
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.forEach((headerName, headerValues) -> {
            headers
                    .append("   - ")
                    .append(headerName)
                    .append(": ")
                    .append(String.join(", ", headerValues))
                    .append("\n");
        });

        // Here you would need to capture the response body as well if needed,
        // but it requires wrapping the response body which is more complex in reactive streams.
        log.info("\n<< -- << -- << -- << RESPONSE << -- << -- << -- <<\n"
                        + "Status: {}\n"
                        + "Headers:\n{}\n"
                        + "<< -- << -- << -- << RESPONSE << -- << -- << -- <<\n",
                response.getStatusCode(),
                headers
        );
    }

    @Data
    public static class Config {
        private String message;

        private Boolean preLogger;

        private Boolean postLogger;
    }
}
