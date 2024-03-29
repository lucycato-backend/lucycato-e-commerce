package org.lucycato.common;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.lucycato.common.annotation.hexagonal.out.ProducerAdapter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

@ProducerAdapter
public class AsyncLoggingProducer {
    private final KafkaProducer<String, String> kafkaProducer;

    private final String topic;

    public AsyncLoggingProducer(
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

    public void sendLogMessage(String logKey, String logMessage) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, logKey, logMessage);
        kafkaProducer.send(record, (recordMetadata, e) -> {
            if (e != null) e.printStackTrace();
        });
    }
}
