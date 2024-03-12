package org.lucycato.taskconsumer.application.port.out;

import org.lucycato.common.model.task.TaskKey;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryR2dbcEntity;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryStatus;
import reactor.core.publisher.Mono;

public interface SendResultTaskPort {
    Mono<Void> sendTaskResultJsonMessage(TaskKey taskKey, String jsonString);
}
