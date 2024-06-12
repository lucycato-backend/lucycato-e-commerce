package org.lucycato.productqueryservice.application.port.in.command;


import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productqueryservice.domain.enums.CourseGenre;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecificCourseLectureSearchCommand extends SelfValidating<SpecificCourseLectureSearchCommand> {
    @NotNull
    private Long courseId;

    public SpecificCourseLectureSearchCommand(Long courseId) {
        this.courseId = courseId;

        this.validateSelf();
    }
}
