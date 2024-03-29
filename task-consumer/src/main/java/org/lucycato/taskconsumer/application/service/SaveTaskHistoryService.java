package org.lucycato.taskconsumer.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.UseCase;
import org.lucycato.taskconsumer.application.port.in.SaveTaskHistoryAndSendTaskResultCommand;
import org.lucycato.taskconsumer.application.port.in.SaveTaskHistoryUseCase;
import org.lucycato.taskconsumer.application.port.out.SaveTaskHistoryPort;
import org.lucycato.taskconsumer.application.port.out.SendResultTaskPort;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryR2dbcEntity;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryStatus;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class SaveTaskHistoryService implements SaveTaskHistoryUseCase {

    private final SendResultTaskPort sendResultTaskPort;

    private final SaveTaskHistoryPort saveTaskHistoryPort;

    @Override
    public Mono<SaveTaskHistoryR2dbcEntity> saveTaskHistoryAndSendTaskResult(SaveTaskHistoryAndSendTaskResultCommand command) {
        return saveTaskHistoryPort.createSaveTaskHistory(command.getTaskKey(), SaveTaskHistoryStatus.REQUEST)
                .flatMap(entity ->
                        sendResultTaskPort.sendTaskResultJsonMessage(command.getTaskKey(), command.getValueJsonString())
                                .then(saveTaskHistoryPort.modifySaveTaskHistoryStatus(entity, SaveTaskHistoryStatus.SUCCESS))
                                .onErrorResume(error -> saveTaskHistoryPort.modifySaveTaskHistoryStatus(entity, SaveTaskHistoryStatus.FAIL))
                );
    }
}
