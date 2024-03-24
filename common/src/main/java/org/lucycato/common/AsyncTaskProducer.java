package org.lucycato.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.lucycato.common.annotation.out.ProducerAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.model.task.TaskKey;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

@ProducerAdapter
public class AsyncTaskProducer {
    private final KafkaProducer<String, String> kafkaProducer;
    private final ObjectMapper objectMapper;
    private final String topic;

    public AsyncTaskProducer(
            @Value("${kafka.clusters.bootstrapservers:null}") String bootstrapServers,
            @Value("${kafka.task.topic:null}") String topic,
            ObjectMapper objectMapper
    ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.topic = topic;
        this.kafkaProducer = new KafkaProducer<>(props);
        this.objectMapper = objectMapper;
    }

    public void sendAsyncTask(TaskKey asyncTaskKey, Object asyncTaskValue) throws Exception {
        try {
            String keyJsonStringToProducer = objectMapper.writeValueAsString(asyncTaskKey);
            String valueJsonStringToProducer = objectMapper.writeValueAsString(asyncTaskValue);
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, keyJsonStringToProducer, valueJsonStringToProducer);
            kafkaProducer.send(record).get();
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.KAFKA_SEND_FAIL);
        }
    }
}
