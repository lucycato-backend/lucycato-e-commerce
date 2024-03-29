package org.lucycato.webflux;

import lombok.RequiredArgsConstructor;
import org.lucycato.webflux.resolver.WebFluxAdminMemberMethodArgumentResolver;
import org.lucycato.webflux.resolver.WebFluxAppMemberMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebfluxConfig implements WebFluxConfigurer {
    private final WebFluxAdminMemberMethodArgumentResolver webFluxAdminMemberMethodArgumentResolver;
    private final WebFluxAppMemberMethodArgumentResolver webFluxAppMemberMethodArgumentResolver;

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        WebFluxConfigurer.super.configureArgumentResolvers(configurer);
        configurer.addCustomResolver(webFluxAdminMemberMethodArgumentResolver);
        configurer.addCustomResolver(webFluxAppMemberMethodArgumentResolver);
    }
}
