package org.lucycato.common;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.lucycato.common.annotation.out.ProducerAdapter;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import java.util.Properties;

@ProducerAdapter
public class LoggingProducer {
    private final KafkaProducer<String, String> kafkaProducer;

    private final String topic;

    public LoggingProducer(
            @Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
            @Value("${kafka.logging.topic}") String topic
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.kafkaProducer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    public Mono<Void> sendLogMessage(String logKey, String logMessage) {
        return Mono.just(new ProducerRecord<>(topic, logKey, logMessage))
                .flatMap(record -> Mono.create(sink -> kafkaProducer.send(record, ((metadata, exception) -> {
                    if (exception != null) {
                        exception.printStackTrace();
                    }
                    sink.success();
                }))));
    }
}
