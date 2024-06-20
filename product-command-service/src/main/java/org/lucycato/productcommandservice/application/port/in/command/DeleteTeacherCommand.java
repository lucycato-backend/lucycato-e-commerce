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
    private Long requestAdminUserId;

    @NotNull
    private Long teacherId;

    public DeleteTeacherCommand(Long requestAdminUserId, Long teacherId) {
        this.requestAdminUserId = requestAdminUserId;
        this.teacherId = teacherId;

        this.validateSelf();
    }
}
