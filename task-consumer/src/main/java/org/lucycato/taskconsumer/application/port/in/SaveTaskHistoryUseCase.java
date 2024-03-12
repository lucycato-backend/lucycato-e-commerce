package org.lucycato.taskconsumer.application.port.in;

import org.lucycato.taskconsumer.domain.SaveTaskHistoryR2dbcEntity;
import reactor.core.publisher.Mono;

public interface SaveTaskHistoryUseCase {
    Mono<SaveTaskHistoryR2dbcEntity> saveTaskHistoryAndSendTaskResult(SaveTaskHistoryAndSendTaskResultCommand command);
}
