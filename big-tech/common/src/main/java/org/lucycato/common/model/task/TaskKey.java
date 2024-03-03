package org.lucycato.common.model.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TaskKey extends SelfValidating<TaskKey> {
    @NotBlank
    private String transactionUUID;

    @NotNull
    private TaskKeyCategory taskKeyCategory;

    public TaskKey(String transactionUUID, TaskKeyCategory taskKeyCategory) throws Exception {
        this.transactionUUID = transactionUUID;
        this.taskKeyCategory = taskKeyCategory;

        this.validateSelf();
    }
}
