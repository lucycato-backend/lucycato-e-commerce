package org.lucycato.usercoursequeryservice.application.port.in.command;


import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserCourseDetailSearchCommand extends SelfValidating<UserCourseDetailSearchCommand> {
    @NotNull
    private Long courseId;

    public UserCourseDetailSearchCommand(Long courseId) {
        this.courseId = courseId;

        this.validateSelf();
    }
}
