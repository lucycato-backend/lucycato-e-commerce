package org.lucycato.loggingconsumer.adapter.in.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.lucycato.common.annotation.in.ConsumerAdapter;
import org.lucycato.loggingconsumer.application.port.in.HandleLoggingCommand;
import org.lucycato.loggingconsumer.application.port.in.LoggingUseCase;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.stream.IntStream;

@ConsumerAdapter
public class LoggingConsumer {
    private final String CONSUMER_GROUP_ID = "lucycato-logging-consumer-group";

    public LoggingConsumer(
            @Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
            @Value("${kafka.logging.topic}") String topic,
            LoggingUseCase loggingUseCase
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", CONSUMER_GROUP_ID);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        IntStream.range(0, 3).forEach(i -> {
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Collections.singletonList(topic));

            Flux.just("")
                    .publishOn(Schedulers.boundedElastic())
                    .flatMap(it -> Flux.<ConsumerRecord<String, String>>create(sink -> {
                        while (!sink.isCancelled()) {
                            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                            for (ConsumerRecord<String, String> record : records) {
                                sink.next(record);
                            }
                        }
                    }))
                    .map(record -> new HandleLoggingCommand(record.key(), record.value()))
                    .doOnNext(loggingUseCase::handleLogging)
                    .subscribe();
        });
    }
}
