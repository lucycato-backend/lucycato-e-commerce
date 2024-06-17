package org.lucycato.productqueryservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecificAppCourseTextEBookSearchCommand extends SelfValidating<SpecificAppCourseTextEBookSearchCommand> {
    @NotNull
    private Long appUserId;

    @NotNull
    private Long courseId;

    public SpecificAppCourseTextEBookSearchCommand(Long appUserId, Long courseId) {
        this.appUserId = appUserId;
        this.courseId = courseId;
        
        this.validateSelf();
    }
}
