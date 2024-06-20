package org.lucycato.productcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DeleteLectureCommand extends SelfValidating<DeleteLectureCommand> {
    @NotNull
    private Long lectureId;

    public DeleteLectureCommand(Long lectureId) {
        this.lectureId = lectureId;

        this.validateSelf();
    }
}
