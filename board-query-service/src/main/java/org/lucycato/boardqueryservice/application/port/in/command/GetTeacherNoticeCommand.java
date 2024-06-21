package org.lucycato.boardqueryservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GetTeacherNoticeCommand extends SelfValidating<GetTeacherNoticeCommand> {

    @NotNull
    private Long teacherId;

    public GetTeacherNoticeCommand(Long teacherId) {
        this.teacherId = teacherId;

        this.validateSelf();
    }
}
