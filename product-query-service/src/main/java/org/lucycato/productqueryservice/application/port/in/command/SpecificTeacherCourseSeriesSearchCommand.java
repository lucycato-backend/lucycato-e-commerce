package org.lucycato.productqueryservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecificTeacherCourseSeriesSearchCommand extends SelfValidating<SpecificTeacherCourseSeriesSearchCommand> {
    @NotNull
    private Long teacherId;

    public SpecificTeacherCourseSeriesSearchCommand(Long teacherId) {
        this.teacherId = teacherId;

        this.validateSelf();
    }
}
