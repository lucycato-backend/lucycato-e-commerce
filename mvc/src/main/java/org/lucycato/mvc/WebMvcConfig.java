package org.lucycato.mvc;

import lombok.RequiredArgsConstructor;
import org.lucycato.mvc.filter.WebMvcFilter;
import org.lucycato.mvc.resolver.WebMvcAdminMemberMethodArgumentResolver;
import org.lucycato.mvc.resolver.WebMvcAppMemberMethodArgumentResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final WebMvcFilter webMvcFilter;
    private final WebMvcAdminMemberMethodArgumentResolver webMvcAdminMemberMethodArgumentResolver;
    private final WebMvcAppMemberMethodArgumentResolver webMvcAppMemberMethodArgumentResolver;

    @Bean
    public FilterRegistrationBean<WebMvcFilter> mvcFilterW() {
        FilterRegistrationBean<WebMvcFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(webMvcFilter);
        return registrationBean;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(webMvcAdminMemberMethodArgumentResolver);
        resolvers.add(webMvcAppMemberMethodArgumentResolver);
    }
}