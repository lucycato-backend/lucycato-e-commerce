package org.lucycato.taskconsumer.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.kafka.TaskKey;

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