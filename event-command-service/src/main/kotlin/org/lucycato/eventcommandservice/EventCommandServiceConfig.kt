package org.lucycato.eventcommandservice

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("org.lucycato.common", "org.lucycato.webflux")
class EventCommandServiceConfig {
}