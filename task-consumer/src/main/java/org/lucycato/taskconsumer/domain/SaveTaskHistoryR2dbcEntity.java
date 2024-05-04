package org.lucycato.taskconsumer.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "save_task_history")
@Setter
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTaskHistoryR2dbcEntity {
    @Id
    private Long id;

    private String taskKeyCategory;

    private String taskHistoryStatus;

    private String transactionUUID;

    @CreatedDate
    private LocalDateTime createdAt;

//    public static SaveTaskHistoryR2dbcEntity register(
//            TaskKeyCategory taskKeyCategory,
//            SaveTaskHistoryStatus taskHistoryStatus,
//            String transactionUUID
//    ) {
//        return SaveTaskHistoryR2dbcEntity.builder()
//                .taskKeyCategory(taskKeyCategory.name())
//                .taskHistoryStatus(taskHistoryStatus.name())
//                .transactionUUID(transactionUUID)
//                .build();
//    }
}
