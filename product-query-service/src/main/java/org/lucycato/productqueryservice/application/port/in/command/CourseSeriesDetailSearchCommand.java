package org.lucycato.productqueryservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CourseSeriesDetailSearchCommand extends SelfValidating<CourseSeriesDetailSearchCommand> {

    @NotNull
    private Long courseSeriesId;

    public CourseSeriesDetailSearchCommand(Long courseSeriesId) {
        this.courseSeriesId = courseSeriesId;

        this.validateSelf();
    }
}
