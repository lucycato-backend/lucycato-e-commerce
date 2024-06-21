package org.lucycato.taskconsumer.adapter.in.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.lucycato.common.annotation.hexagonal.in.ConsumerAdapter;
import org.lucycato.common.kafka.AsyncTask;
import org.lucycato.taskconsumer.application.port.in.NotificationUseCase;
import org.lucycato.taskconsumer.application.port.in.command.SendNotificationCommand;
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
public class NotificationConsumer {
    private final String GROUP_ID = "LUCYCATO-NOTIFICATION-CONSUMER_GROUP";
    private final String TOPIC = "LUCYCATO_NOTIFICATION_SERVICE";

    public NotificationConsumer(
            @Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
            ObjectMapper objectMapper,
            NotificationUseCase notificationUseCase
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", GROUP_ID);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(TOPIC));

        IntStream.range(0, 3).forEach(i -> {
            Flux.just(Duration.ofMillis(100))
                    .publishOn(Schedulers.boundedElastic())
                    .flatMap(duration -> Flux.<String>create(sink -> {
                        while (!sink.isCancelled()) {
                            ConsumerRecords<String, String> records = consumer.poll(duration);
                            for (ConsumerRecord<String, String> record : records) {
                                sink.next(record.value());
                            }
                        }
                    }))
                    .flatMap(value -> Mono.fromCallable(() -> objectMapper.readValue(value, AsyncTask.class)))
                    .flatMap(asyncTask -> {
                        return Mono.empty();
                    })
                    .subscribe();
        });
    }
}
