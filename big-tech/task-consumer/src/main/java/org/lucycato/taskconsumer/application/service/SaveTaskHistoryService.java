package org.lucycato.taskconsumer.application.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.in.UseCase;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.model.task.TaskKey;
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
    private final ObjectMapper objectMapper;

    private final SendResultTaskPort sendResultTaskPort;

    private final SaveTaskHistoryPort saveTaskHistoryPort;

    @Override
    public Mono<SaveTaskHistoryR2dbcEntity> saveTaskHistoryAndSendTaskResult(SaveTaskHistoryAndSendTaskResultCommand command) {
        return Mono.fromCallable(() -> objectMapper.readValue(command.getTaskKeyJsonString(), TaskKey.class))
                .onErrorResume(JsonMappingException.class, error -> Mono.error(ErrorCodeImpl.JSON_PARSING.build()))
                .flatMap(taskKey -> saveTaskHistoryPort.createSaveTaskHistory(taskKey, SaveTaskHistoryStatus.REQUEST)
                        .flatMap(entity -> sendResultTaskPort.sendTaskResultJsonMessage(taskKey, command.getValueJsonString())
                                .then(saveTaskHistoryPort.modifySaveTaskHistoryStatus(entity, SaveTaskHistoryStatus.SUCCESS))
                                .onErrorResume(error -> saveTaskHistoryPort.modifySaveTaskHistoryStatus(entity, SaveTaskHistoryStatus.FAIL))
                        ));
    }
}
