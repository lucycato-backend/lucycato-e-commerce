package org.lucycato.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.lucycato.common.annotation.out.ProducerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Properties;

@ProducerAdapter
public class TaskProducer {
    private final LoggingProducer loggingProducer;
    private final PrintStackTraceManager printStackTraceManager;
    private final KafkaProducer<String, String> kafkaProducer;
    private final ObjectMapper objectMapper;
    private final String bootstrapServers;
    private final String topic;

    public TaskProducer(
            @Value("${kafka.clusters.bootstrapservers:null}") String bootstrapServers,
            @Value("${kafka.task.topic:null}") String topic,
            LoggingProducer loggingProducer,
            PrintStackTraceManager printStackTraceManager,
            ObjectMapper objectMapper
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
        this.kafkaProducer = new KafkaProducer<>(props);
        this.objectMapper = objectMapper;
        this.loggingProducer = loggingProducer;
        this.printStackTraceManager = printStackTraceManager;
    }

    public Mono<Void> sendTask(String taskKey, Object taskValue) {
        if (bootstrapServers == null || topic == null) return Mono.error(new RuntimeException(""));
        return Mono.fromCallable(() -> objectMapper.writeValueAsString(taskValue))
                .map(taskValueJsonString -> new ProducerRecord<>(topic, taskKey, taskValueJsonString))
                .flatMap(record -> Mono.create(sink -> kafkaProducer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        exception.printStackTrace();
                        String stackTraceString = printStackTraceManager.getStackTraceAsString(exception);
                        loggingProducer.sendLogMessage("kafka exception", stackTraceString);
                        sink.error(exception);
                    } else {
                        sink.success();
                    }
                })));
    }
}
