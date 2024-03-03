package org.lucycato.taskconsumer.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.lucycato.common.annotation.in.ConsumerAdapter;
import org.lucycato.taskconsumer.application.port.in.SaveTaskHistoryAndSendTaskResultCommand;
import org.lucycato.taskconsumer.application.port.in.SaveTaskHistoryUseCase;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.stream.IntStream;

@ConsumerAdapter
@RequiredArgsConstructor
public class SaveTaskHistoryConsumer {
    private final String GROUP_ID = "lucycato-save-task-history-consumer-group";
    public SaveTaskHistoryConsumer(
            @Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
            @Value("${kafka.task.topic}") String topic,
            SaveTaskHistoryUseCase saveTaskHistoryUseCase
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", GROUP_ID);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        IntStream.range(0, 3).forEach(i -> {
            Flux.just(Duration.ofMillis(100))
                    .publishOn(Schedulers.boundedElastic())
                    .flatMap(duration -> Flux.<ConsumerRecord<String, String>>create(sink -> {
                        while (!sink.isCancelled()) {
                            ConsumerRecords<String, String> records = consumer.poll(duration);
                            for (ConsumerRecord<String, String> record : records) {
                                sink.next(record);
                            }
                        }
                    }))
                    .map(record -> new SaveTaskHistoryAndSendTaskResultCommand(record.key(), record.value()))
                    .doOnNext(saveTaskHistoryUseCase::saveTaskHistoryAndSendTaskResult)
                    .subscribe();
        });
    }
}
