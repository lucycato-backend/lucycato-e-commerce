package org.lucycato.productqueryservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecificAdminCourseLectureSearchCommand extends SelfValidating<SpecificAdminCourseLectureSearchCommand> {
    @NotNull
    private Long adminUserId;

    @NotNull
    private Long courseId;

    public SpecificAdminCourseLectureSearchCommand(Long adminUserId, Long courseId) {
        this.adminUserId = adminUserId;
        this.courseId = courseId;

        this.validateSelf();
    }
}
