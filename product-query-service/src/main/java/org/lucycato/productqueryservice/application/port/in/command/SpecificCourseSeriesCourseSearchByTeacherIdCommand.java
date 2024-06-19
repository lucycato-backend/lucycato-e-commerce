package org.lucycato.productqueryservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecificCourseSeriesCourseSearchByTeacherIdCommand extends SelfValidating<SpecificCourseSeriesCourseSearchByTeacherIdCommand> {
    @NotNull
    private Long teacherId;

    public SpecificCourseSeriesCourseSearchByTeacherIdCommand(Long teacherId) {
        this.teacherId = teacherId;

        this.validateSelf();
    }
}
