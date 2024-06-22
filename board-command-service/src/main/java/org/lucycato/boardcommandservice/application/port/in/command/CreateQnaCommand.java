package org.lucycato.boardcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateQnaCommand extends SelfValidating<CreateQnaCommand> {

    @NotNull
    private Long id;

    @NotNull
    private Long teacherId;

    @NotNull
    private Long lectureId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public CreateQnaCommand(Long id, Long teacherId, Long lectureId, String title, String content) {
        this.id = id;
        this.teacherId = teacherId;
        this.lectureId = lectureId;
        this.title = title;
        this.content = content;

        this.validateSelf();
    }
}
