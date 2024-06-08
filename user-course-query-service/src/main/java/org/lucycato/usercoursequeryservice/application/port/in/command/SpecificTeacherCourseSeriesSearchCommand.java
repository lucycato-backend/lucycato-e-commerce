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

    private Boolean isSimple;


    public SpecificTeacherCourseSeriesSearchCommand(Long teacherId, Boolean isSimple) {
        this.teacherId = teacherId;
        this.isSimple = isSimple;

        this.validateSelf();
    }
}
