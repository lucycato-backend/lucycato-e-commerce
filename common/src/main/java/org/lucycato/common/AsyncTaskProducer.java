package org.lucycato.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.lucycato.common.annotation.hexagonal.out.ProducerAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.kafka.AsyncTask;
import org.lucycato.common.kafka.TaskKey;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

@ProducerAdapter
public class AsyncTaskProducer {
    private final KafkaProducer<String, String> kafkaProducer;
    private final ObjectMapper objectMapper;

    public AsyncTaskProducer(
            @Value("${kafka.clusters.bootstrapservers:null}") String bootstrapServers,
            ObjectMapper objectMapper
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.kafkaProducer = new KafkaProducer<>(props);
        this.objectMapper = objectMapper;
    }

    public <T> void sendAsyncTask(AsyncTask<T> asyncTask) throws Exception {
        try {
            String asyncTaskJson = objectMapper.writeValueAsString(asyncTask);
            ProducerRecord<String, String> record = new ProducerRecord<>(asyncTask.getTaskKey().getTopic(), asyncTaskJson);
            kafkaProducer.send(record);
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.KAFKA_SEND_FAIL);
        }
    }
}



