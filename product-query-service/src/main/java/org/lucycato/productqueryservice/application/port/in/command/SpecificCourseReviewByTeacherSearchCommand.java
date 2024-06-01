package org.lucycato.productqueryservice.application.port.in.command;


import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecificCourseReviewByTeacherSearchCommand extends SelfValidating<SpecificCourseReviewByTeacherSearchCommand> {
    @NotNull
    private Long teacherId;

    public SpecificCourseReviewByTeacherSearchCommand(Long teacherId) {
        this.teacherId = teacherId;

        this.validateSelf();
    }
}
