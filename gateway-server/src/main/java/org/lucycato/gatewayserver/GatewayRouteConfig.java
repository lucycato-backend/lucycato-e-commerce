package org.lucycato.gatewayserver;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-auth-command-common", r -> r.path("/user-auth-command/**")
                        .filters(f -> f.rewritePath("/user-auth-command/(?<segment>.*)", "/${segment}"))
                        .uri("lb://user-auth-command-service"))

                .route("user-auth-command-service", r -> r.path("/open-api/user-auth-command/**", "/api/admin/user-auth-command/**", "/api/app/user-auth-command/**")
                        .uri("lb://user-auth-command-service"))
                .route("user-auth-query-service", r -> r.path("/open-api/user-auth-query/**", "/api/admin/user-auth-query/**", "/api/app/user-auth-query/**")
                        .uri("lb://user-auth-query-service"))
                .route("notification-command-service", r -> r.path("/open-api/notification-command/**", "/api/admin/notification-command/**", "/api/app/notification-command/**")
                        .uri("lb://notification-command-service"))
                .route("notification-query-service", r -> r.path("/open-api/notification-query/**", "/api/admin/notification-query/**", "/api/app/notification-query/**")
                        .uri("lb://notification-query-service"))
                .route("product-command-service", r -> r.path("/open-api/product-command/**", "/api/admin/product-command/**", "/api/app/product-command/**")
                        .uri("lb://product-command-service"))
                .route("product-query-service", r -> r.path("/open-api/product-query/**", "/api/admin/product-query/**", "/api/app/product-query/**")
                        .uri("lb://product-query-service"))
                .route("board-command-service", r -> r.path("/open-api/board-command/**", "/api/admin/board-command/**", "/api/app/board-command/**")
                        .uri("lb://board-command-service"))
                .route("board-query-service", r -> r.path("/open-api/board-query/**", "/api/admin/board-query/**", "/api/app/board-query/**")
                        .uri("lb://board-query-service"))
                .route("event-command-service", r -> r.path("/open-api/event-command/**", "/api/admin/event-command/**", "/api/app/event-command/**")
                        .uri("lb://event-command-service"))
                .route("event-query-service", r -> r.path("/open-api/event-query/**", "/api/admin/event-query/**", "/api/app/event-query/**")
                        .uri("lb://event-query-service"))
                .route("order-command-service", r -> r.path("/open-api/order-command/**", "/api/admin/order-command/**", "/api/app/order-command/**")
                        .uri("lb://order-command-service"))
                .route("order-query-service", r -> r.path("/open-api/order-query/**", "/api/admin/order-query/**", "/api/app/order-query/**")
                        .uri("lb://order-query-service"))
                .build();
    }
}
