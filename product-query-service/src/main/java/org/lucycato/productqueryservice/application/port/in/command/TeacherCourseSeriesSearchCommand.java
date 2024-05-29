package org.lucycato.productqueryservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

import java.util.function.BinaryOperator;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TeacherCourseSeriesSearchCommand extends SelfValidating<TeacherCourseSeriesSearchCommand> {

    private TeachingGenre teachingGenre;

    private Boolean isSimple;

    public TeacherCourseSeriesSearchCommand(TeachingGenre teachingGenre, Boolean isSimple) {
        this.teachingGenre = teachingGenre;
        this.isSimple = isSimple;

        this.validateSelf();
    }
}
