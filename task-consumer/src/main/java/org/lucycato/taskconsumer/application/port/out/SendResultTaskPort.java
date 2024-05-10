package org.lucycato.taskconsumer.application.port.out;

import org.lucycato.common.kafka.TaskKey;
import reactor.core.publisher.Mono;

public interface SendResultTaskPort {
    Mono<Void> sendTaskResultJsonMessage(TaskKey taskKey, String jsonString);
}
