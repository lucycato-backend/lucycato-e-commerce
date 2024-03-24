package org.lucycato.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.lucycato.common.annotation.out.ProducerAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.model.task.TaskKey;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.Properties;

@ProducerAdapter
public class AsyncTaskProducer {
    private final AsyncLoggingProducer asyncLoggingProducer;
    private final PrintStackTraceManager printStackTraceManager;
    private final KafkaProducer<String, String> kafkaProducer;
    private final ObjectMapper objectMapper;
    private final String bootstrapServers;
    private final String topic;

    public AsyncTaskProducer(
            @Value("${kafka.clusters.bootstrapservers:null}") String bootstrapServers,
            @Value("${kafka.task.topic:null}") String topic,
            AsyncLoggingProducer asyncLoggingProducer,
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
        this.asyncLoggingProducer = asyncLoggingProducer;
        this.printStackTraceManager = printStackTraceManager;
    }

    public Mono<Void> sendTask(TaskKey taskKey, Object taskValue) {
        if (bootstrapServers == null || topic == null) return Mono.error(new ApiExceptionImpl(ErrorCodeImpl.KAFKA_SEND_TASK_FAIL));
        return Mono.fromCallable(() -> Tuples.of(objectMapper.writeValueAsString(taskKey), objectMapper.writeValueAsString(taskValue)))
                .onErrorResume(JsonProcessingException.class, error -> Mono.error(new ApiExceptionImpl(ErrorCodeImpl.KAFKA_SEND_TASK_FAIL)))
                .map(tuple -> new ProducerRecord<>(topic, tuple.getT1(), tuple.getT2()))
                .flatMap(record -> Mono.create(sink -> kafkaProducer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        exception.printStackTrace();
                        String stackTraceString = printStackTraceManager.getStackTraceAsString(exception);
                        asyncLoggingProducer.sendLogMessage("kafka exception", stackTraceString);
                        sink.error(exception);
                    } else {
                        sink.success();
                    }
                })));
    }
}
