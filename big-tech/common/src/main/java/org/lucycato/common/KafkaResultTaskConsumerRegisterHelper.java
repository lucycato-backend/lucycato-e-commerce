package org.lucycato.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Getter
@Component
public class KafkaResultTaskConsumerRegisterHelper {
    private final String bootstrapServers;

    @Getter
    private final String topic;

    public KafkaResultTaskConsumerRegisterHelper(
            @Value("${kafka.clusters.bootstrapservers:null}") String bootstrapServers,
            @Value("${kafka.task.result.topic:null}") String topic
    ) {
        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
    }

    public Properties createPropsConsumerTaskResultTopic(String groupId) {
        if (bootstrapServers == null || topic == null) throw new RuntimeException("server environment is not exist please setting environment");
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", groupId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
}
