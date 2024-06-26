package org.lucycato.productcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DeleteCourseSeriesCommand extends SelfValidating<DeleteCourseSeriesCommand> {
    @NotNull
    private Long requestAdminUserId;

    @NotNull
    private Long courseSeriesId;

    public DeleteCourseSeriesCommand(Long requestAdminUserId, Long courseSeriesId) {
        this.requestAdminUserId = requestAdminUserId;
        this.courseSeriesId = courseSeriesId;

        this.validateSelf();
    }
}
