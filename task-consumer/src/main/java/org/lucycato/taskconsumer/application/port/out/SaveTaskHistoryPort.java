package org.lucycato.taskconsumer.application.port.out;

import org.lucycato.common.kafka.TaskKey;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryR2dbcEntity;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryStatus;
import reactor.core.publisher.Mono;

public interface SaveTaskHistoryPort {
    Mono<SaveTaskHistoryR2dbcEntity> createSaveTaskHistory(TaskKey taskKey, SaveTaskHistoryStatus taskHistoryStatus);

    Mono<SaveTaskHistoryR2dbcEntity> modifySaveTaskHistoryStatus(SaveTaskHistoryR2dbcEntity entity, SaveTaskHistoryStatus taskHistoryStatus);
}
