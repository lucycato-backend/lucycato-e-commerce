package org.lucycato.productqueryservice.application.port.in.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productqueryservice.domain.enums.TeacherStatus;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TeacherSearchCommand extends SelfValidating<TeacherSearchCommand> {

    private TeachingGenre teachingGenre;

    private TeacherStatus teacherStatus;

    public TeacherSearchCommand(TeachingGenre teachingGenre, TeacherStatus teacherStatus) {
        this.teachingGenre = teachingGenre;
        this.teacherStatus = teacherStatus;

        this.validateSelf();
    }
}
