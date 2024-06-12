package org.lucycato.userauthcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@ComponentScan({"org.lucycato.common", "org.lucycato.mvc"})
@EnableJpaAuditing
public class UserAuthCommandServiceConfig {

}