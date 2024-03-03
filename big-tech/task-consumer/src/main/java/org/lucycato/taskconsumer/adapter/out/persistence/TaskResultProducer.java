package org.lucycato.taskconsumer.adapter.out.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.lucycato.common.LoggingProducer;
import org.lucycato.common.PrintStackTraceManager;
import org.lucycato.common.annotation.out.ProducerAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.model.task.TaskKey;
import org.lucycato.taskconsumer.application.port.out.SendResultTaskPort;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import java.util.Properties;

@ProducerAdapter
public class TaskResultProducer implements SendResultTaskPort {
    private final LoggingProducer loggingProducer;
    private final PrintStackTraceManager printStackTraceManager;
    private final KafkaProducer<String, String> kafkaProducer;
    private final ObjectMapper objectMapper;
    private final String topic;

    public TaskResultProducer(
            LoggingProducer loggingProducer,
            PrintStackTraceManager printStackTraceManager,
            ObjectMapper objectMapper,
            @Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
            @Value("${kafka.task.result.topic}") String topic
    ) {
        this.loggingProducer = loggingProducer;
        this.printStackTraceManager = printStackTraceManager;
        this.objectMapper = objectMapper;

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.kafkaProducer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    @Override
    public Mono<Void> sendTaskResultJsonMessage(TaskKey taskKey, String jsonString) {
        return Mono.fromCallable(() -> objectMapper.writeValueAsString(taskKey))
                .onErrorResume(JsonProcessingException.class, error -> Mono.error(ErrorCodeImpl.JSON_PARSING.build()))
                .map(taskKeyJsonString -> new ProducerRecord<String, String>(topic, taskKeyJsonString, jsonString))
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
