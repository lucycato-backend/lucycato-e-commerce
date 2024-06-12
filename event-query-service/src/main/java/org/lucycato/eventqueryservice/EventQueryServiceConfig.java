package org.lucycato.eventqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.lucycato.common", "org.lucycato.webflux"})
public class EventQueryServiceConfig {
}
