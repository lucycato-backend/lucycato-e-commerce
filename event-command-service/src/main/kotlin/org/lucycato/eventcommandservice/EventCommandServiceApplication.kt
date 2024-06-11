package org.lucycato.eventcommandservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class EventCommandServiceApplication
fun main(args: Array<String>) {
    runApplication<EventCommandServiceApplication>(*args)
}
