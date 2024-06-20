package org.lucycato.productcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DeleteTeacherCommand extends SelfValidating<DeleteTeacherCommand> {
    @NotNull
    private Long teacherId;

    public DeleteTeacherCommand(Long teacherId) {
        this.teacherId = teacherId;

        this.validateSelf();
    }
}
