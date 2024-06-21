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
public class CreateExamStoryCommand extends SelfValidating<CreateExamStoryCommand> {

    @NotNull
    private Long id;

    @NotNull
    private Long teacherId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String type;

    public CreateExamStoryCommand(Long id, Long teacherId, String title, String content, String type) {
        this.id = id;
        this.teacherId = teacherId;
        this.title = title;
        this.content = content;
        this.type = type;

        this.validateSelf();
    }
}
