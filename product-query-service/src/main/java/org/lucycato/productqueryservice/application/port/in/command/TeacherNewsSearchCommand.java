package org.lucycato.productqueryservice.application.port.in.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productqueryservice.domain.enums.TeacherNewsCategory;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TeacherNewsSearchCommand extends SelfValidating<TeacherNewsSearchCommand> {

    private TeachingGenre teachingGenre;

    private TeacherNewsCategory teacherNewsCategory;

    public TeacherNewsSearchCommand(TeachingGenre teachingGenre, TeacherNewsCategory teacherNewsCategory) {
        this.teachingGenre = teachingGenre;
        this.teacherNewsCategory = teacherNewsCategory;

        this.validateSelf();
    }
}
