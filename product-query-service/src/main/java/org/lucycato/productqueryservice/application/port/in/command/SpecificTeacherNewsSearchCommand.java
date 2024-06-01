package org.lucycato.productqueryservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productqueryservice.domain.enums.TeacherNewsCategory;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecificTeacherNewsSearchCommand extends SelfValidating<SpecificTeacherNewsSearchCommand> {
    @NotNull
    private Long teacherId;

    private TeacherNewsCategory teacherNewsCategory;

    public SpecificTeacherNewsSearchCommand(Long teacherId, TeacherNewsCategory teacherNewsCategory) {
        this.teacherId = teacherId;
        this.teacherNewsCategory = teacherNewsCategory;

        this.validateSelf();
    }
}
