package com.lucycato.eventservice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@ComponentScan({"org.lucycato.common", "org.lucycato.mvc"})
public class EventServiceConfig {
}
