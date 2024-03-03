package org.lucycato.loggingconsumer.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.lucycato.common.annotation.in.ConsumerAdapter;
import org.lucycato.loggingconsumer.application.port.in.HandleLoggingCommand;
import org.lucycato.loggingconsumer.application.port.in.LoggingUseCase;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.stream.IntStream;

@ConsumerAdapter
@RequiredArgsConstructor
public class LoggingConsumer {
    private final String CONSUMER_GROUP_ID = "lucycato-logging-consumer-group";
    public LoggingConsumer(
            @Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
            @Value("${logging.topic}") String topic,
            LoggingUseCase loggingUseCase
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", CONSUMER_GROUP_ID);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        IntStream.range(0, 2).forEach(i -> {
            Flux.empty()
                    .publishOn(Schedulers.boundedElastic())
                    .flatMap(it -> {
                        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
                        consumer.subscribe(Collections.singletonList(topic));
                        return Mono.just(consumer);
                    })
                    .flatMap(consumer -> Flux.create(sink -> {
                                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                                if (records != null && !records.isEmpty()) {
                                    for (ConsumerRecord<String, String> record : records) {
                                        sink.next(record);
                                    }
                                }
                            })
                            .map(object -> (ConsumerRecord<String, String>) object)
                            .map(record -> new HandleLoggingCommand(record.key(), record.value()))
                            .flatMap(loggingUseCase::handleLogging))
                    .subscribe();
        });
    }
}
