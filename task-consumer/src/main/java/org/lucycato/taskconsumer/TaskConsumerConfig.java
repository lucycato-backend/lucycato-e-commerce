package org.lucycato.taskconsumer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@ComponentScan("com.lucycato.common")
@EnableR2dbcAuditing
@EnableR2dbcRepositories
public class TaskConsumerConfig {
}
