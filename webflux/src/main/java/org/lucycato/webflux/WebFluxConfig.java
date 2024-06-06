package org.lucycato.webflux;

import lombok.RequiredArgsConstructor;
import org.lucycato.webflux.resolver.WebFluxAdminMemberMethodArgumentResolver;
import org.lucycato.webflux.resolver.WebFluxAppMemberMethodArgumentResolver;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebFluxConfig implements WebFluxConfigurer {
    private final WebFluxAdminMemberMethodArgumentResolver webFluxAdminMemberMethodArgumentResolver;
    private final WebFluxAppMemberMethodArgumentResolver webFluxAppMemberMethodArgumentResolver;

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        WebFluxConfigurer.super.configureArgumentResolvers(configurer);
        configurer.addCustomResolver(webFluxAdminMemberMethodArgumentResolver);
        configurer.addCustomResolver(webFluxAppMemberMethodArgumentResolver);
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }
}
