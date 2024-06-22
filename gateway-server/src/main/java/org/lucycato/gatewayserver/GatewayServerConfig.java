package org.lucycato.gatewayserver;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({"org.lucycato.common", "org.lucycato.webflux"})
@Configuration
public class GatewayServerConfig {
}
