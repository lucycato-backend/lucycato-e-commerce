package org.lucycato.taskconsumer.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveTaskHistoryAndSendTaskResultCommand {

    private String taskKeyJsonString;

    private String valueJsonString;

    public SaveTaskHistoryAndSendTaskResultCommand(String taskKeyJsonString, String valueJsonString) {
        this.taskKeyJsonString = taskKeyJsonString;
        this.valueJsonString = valueJsonString;
    }
}