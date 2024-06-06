package org.lucycato.webflux;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFluxSwaggerConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String version) {
        Info info = new Info()
                .title("Lucycato Document")
                .version(version)
                .description("Lucycato 프로젝트의 API 명세서입니다.");

        String jwtScheme = "jwtAuth";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtScheme);
        Components components = new Components()
                .addSecuritySchemes(jwtScheme, new SecurityScheme()
                        .name(jwtScheme)
                        .type(Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .components(components)
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }

    @Bean
    public OperationCustomizer customizeOperation() {
        return (operation, handlerMethod) -> {
            operation.addParametersItem(new io.swagger.v3.oas.models.parameters.Parameter()
                    .name("Authorization")
                    .in(SecurityScheme.In.HEADER.toString())
                    .required(false)
                    .description("Authorization Header")
                    .schema(new io.swagger.v3.oas.models.media.StringSchema()));
            operation.addParametersItem(new io.swagger.v3.oas.models.parameters.Parameter()
                    .name("X-Lucycato-E-Commerce-Admin_Or_App_Member_Json_String")
                    .in(SecurityScheme.In.HEADER.toString())
                    .required(false)
                    .description("X-Header")
                    .schema(new io.swagger.v3.oas.models.media.StringSchema()));
            return operation;
        };
    }
}