package org.lucycato.productqueryservice.application.port.in.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TeacherSearchCommand extends SelfValidating<TeacherSearchCommand> {

    private TeachingGenre teachingGenre;

    public TeacherSearchCommand(TeachingGenre teachingGenre) {
        this.teachingGenre = teachingGenre;

        this.validateSelf();
    }
}
