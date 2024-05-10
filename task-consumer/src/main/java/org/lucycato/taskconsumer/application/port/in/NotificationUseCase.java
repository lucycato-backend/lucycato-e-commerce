package org.lucycato.taskconsumer.application.port.in;

import org.lucycato.taskconsumer.application.port.in.command.SendNotificationCommand;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryR2dbcEntity;
import reactor.core.publisher.Mono;

public interface NotificationUseCase {
    Mono<SaveTaskHistoryR2dbcEntity> saveTaskHistoryAndSendTaskResult(SaveTaskHistoryAndSendTaskResultCommand command);

    Mono<Void> sendNotification(SendNotificationCommand command);
}
