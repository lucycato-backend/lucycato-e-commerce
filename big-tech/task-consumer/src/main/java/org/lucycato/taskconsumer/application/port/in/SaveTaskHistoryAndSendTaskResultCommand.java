package org.lucycato.taskconsumer.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.model.task.TaskKey;
import org.springframework.scheduling.config.Task;

@Getter
@NoArgsConstructor
public class SaveTaskHistoryAndSendTaskResultCommand {

    private TaskKey taskKey;

    private String valueJsonString;

    public SaveTaskHistoryAndSendTaskResultCommand(TaskKey taskKey, String valueJsonString) {
        this.taskKey = taskKey;
        this.valueJsonString = valueJsonString;
    }
}