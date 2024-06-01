package org.lucycato.productqueryservice.application.port.in.command;


import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecificCourseTextEBookSearchCommand extends SelfValidating<SpecificCourseTextEBookSearchCommand> {
    @NotNull
    private Long courseId;

    public SpecificCourseTextEBookSearchCommand(Long courseId) {
        this.courseId = courseId;

        this.validateSelf();
    }
}
