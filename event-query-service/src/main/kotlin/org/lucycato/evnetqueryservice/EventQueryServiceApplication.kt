package org.lucycato.evnetqueryservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class EventQueryServiceApplication
fun main(args: Array<String>) {
    runApplication<EventQueryServiceApplication>(*args)
}
