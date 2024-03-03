package org.lucycato.taskconsumer.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.out.PersistenceAdapter;
import org.lucycato.common.model.task.TaskKey;
import org.lucycato.taskconsumer.application.port.out.SaveTaskHistoryPort;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryR2dbcEntity;
import org.lucycato.taskconsumer.domain.SaveTaskHistoryStatus;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class SaveTaskHistoryPersistenceAdapter implements SaveTaskHistoryPort {
    private final SaveTaskHistoryReactiveRepository saveTaskHistoryReactiveRepository;

    @Override
    public Mono<SaveTaskHistoryR2dbcEntity> createSaveTaskHistory(TaskKey taskKey, SaveTaskHistoryStatus taskHistoryStatus) {
        return saveTaskHistoryReactiveRepository.save(SaveTaskHistoryR2dbcEntity.register(
                taskKey.getTaskKeyCategory(),
                taskHistoryStatus,
                taskKey.getTransactionUUID()
        ));
    }

    @Override
    public Mono<SaveTaskHistoryR2dbcEntity> modifySaveTaskHistoryStatus(SaveTaskHistoryR2dbcEntity entity, SaveTaskHistoryStatus taskHistoryStatus) {
        entity.setTaskHistoryStatus(taskHistoryStatus.name());
        return saveTaskHistoryReactiveRepository.save(entity);
    }
}
