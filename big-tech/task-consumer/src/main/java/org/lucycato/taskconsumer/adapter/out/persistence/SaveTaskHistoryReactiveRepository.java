package org.lucycato.taskconsumer.adapter.out.persistence;

import org.lucycato.taskconsumer.domain.SaveTaskHistoryR2dbcEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SaveTaskHistoryReactiveRepository extends ReactiveCrudRepository<SaveTaskHistoryR2dbcEntity, Long> {
}
