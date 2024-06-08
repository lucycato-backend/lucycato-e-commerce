package org.lucycato.usercoursequeryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@ComponentScan({"org.lucycato.common", "org.lucycato.webflux"})
@EnableR2dbcRepositories
@EnableR2dbcAuditing
public class UserCourseQueryServiceConfig {

}
