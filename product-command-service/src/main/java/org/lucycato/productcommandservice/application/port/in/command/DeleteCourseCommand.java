package org.lucycato.productcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DeleteCourseCommand extends SelfValidating<DeleteCourseCommand> {
    @NotNull
    private Long requestAdminUserId;

    @NotNull
    private Long courseId;

    public DeleteCourseCommand(Long requestAdminUserId, Long courseId) {
        this.requestAdminUserId = requestAdminUserId;
        this.courseId = courseId;

        this.validateSelf();
    }
}
