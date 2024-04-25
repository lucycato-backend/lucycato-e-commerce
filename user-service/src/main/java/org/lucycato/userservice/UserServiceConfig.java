package org.lucycato.userservice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@ComponentScan({"org.lucycato.common", "org.lucycato.mvc"})
public class UserServiceConfig {
}
