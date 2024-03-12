package org.lucycato.taskconsumer.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lucycato.common.model.task.TaskKey;
import org.lucycato.common.model.task.TaskKeyCategory;
import org.lucycato.taskconsumer.application.port.in.SaveTaskHistoryAndSendTaskResultCommand;
import org.lucycato.taskconsumer.application.port.out.SaveTaskHistoryPort;
import org.lucycato.taskconsumer.application.port.out.SendResultTaskPort;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryR2dbcEntity;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SaveTaskHistoryServiceTest {

    @MockBean
    private SendResultTaskPort sendResultTaskPort;

    @MockBean
    private SaveTaskHistoryPort saveTaskHistoryPort;

    @Autowired
    private SaveTaskHistoryService saveTaskHistoryService;

    @DisplayName("save task history success")
    @Test
    void saveTaskHistoryAndSendTaskResult2() {
        String transactionUUID = UUID.randomUUID().toString();
        TaskKey taskKey = new TaskKey(transactionUUID, TaskKeyCategory.TEST);
        SaveTaskHistoryAndSendTaskResultCommand command = new SaveTaskHistoryAndSendTaskResultCommand(taskKey, "");

        SaveTaskHistoryR2dbcEntity saveEntity = SaveTaskHistoryR2dbcEntity.register(
                taskKey.getTaskKeyCategory(),
                SaveTaskHistoryStatus.REQUEST,
                transactionUUID
        );

        when(saveTaskHistoryPort.createSaveTaskHistory(command.getTaskKey(), SaveTaskHistoryStatus.REQUEST)).thenReturn(Mono.just(
                saveEntity
        ));

        when(sendResultTaskPort.sendTaskResultJsonMessage(command.getTaskKey(), command.getValueJsonString())).thenReturn(Mono.empty());

        when(saveTaskHistoryPort.modifySaveTaskHistoryStatus(saveEntity, SaveTaskHistoryStatus.SUCCESS)).thenReturn(Mono.just(
                SaveTaskHistoryR2dbcEntity.register(
                        taskKey.getTaskKeyCategory(),
                        SaveTaskHistoryStatus.SUCCESS,
                        transactionUUID
                ))
        );

        StepVerifier.create(saveTaskHistoryService.saveTaskHistoryAndSendTaskResult(command))
                .assertNext(entity -> {
                    assertEquals(entity.getTaskKeyCategory(), taskKey.getTaskKeyCategory().name());
                    assertEquals(entity.getTaskHistoryStatus(), SaveTaskHistoryStatus.SUCCESS.name());
                    assertEquals(entity.getTransactionUUID(), taskKey.getTransactionUUID());
                })
                .verifyComplete();
    }

    //TODO: send message exception case error
    @DisplayName("send kafka message fail -> save task history fail")
    @Test
    void saveTaskHistoryAndSendTaskResult3() {
        String transactionUUID = UUID.randomUUID().toString();
        TaskKey taskKey = new TaskKey(transactionUUID, TaskKeyCategory.TEST);
        SaveTaskHistoryAndSendTaskResultCommand command = new SaveTaskHistoryAndSendTaskResultCommand(taskKey, "");

        SaveTaskHistoryR2dbcEntity saveEntity = SaveTaskHistoryR2dbcEntity.register(
                taskKey.getTaskKeyCategory(),
                SaveTaskHistoryStatus.REQUEST,
                transactionUUID
        );

        when(saveTaskHistoryPort.createSaveTaskHistory(command.getTaskKey(), SaveTaskHistoryStatus.REQUEST)).thenReturn(Mono.just(
                saveEntity
        ));

        when(sendResultTaskPort.sendTaskResultJsonMessage(command.getTaskKey(), command.getValueJsonString())).thenReturn(Mono.error(new Exception("")));

        when(saveTaskHistoryPort.modifySaveTaskHistoryStatus(saveEntity, SaveTaskHistoryStatus.FAIL)).thenReturn(Mono.just(
                SaveTaskHistoryR2dbcEntity.register(
                        taskKey.getTaskKeyCategory(),
                        SaveTaskHistoryStatus.FAIL,
                        transactionUUID
                ))
        );

        StepVerifier.create(saveTaskHistoryService.saveTaskHistoryAndSendTaskResult(command))
                .assertNext(entity -> {
                    assertEquals(entity.getTaskKeyCategory(), taskKey.getTaskKeyCategory().name());
                    assertEquals(entity.getTaskHistoryStatus(), SaveTaskHistoryStatus.FAIL.name());
                    assertEquals(entity.getTransactionUUID(), taskKey.getTransactionUUID());
                })
                .verifyComplete();
    }
}