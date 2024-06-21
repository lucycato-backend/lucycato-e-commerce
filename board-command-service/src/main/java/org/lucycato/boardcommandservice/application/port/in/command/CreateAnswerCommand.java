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
public class CreateAnswerCommand extends SelfValidating<CreateAnswerCommand> {

    @NotNull
    private Long id;

    @NotNull
    private Long qnaId;

    @NotBlank
    private String answer;

    public CreateAnswerCommand(Long id, Long qnaId, String answer) {
        this.id = id;
        this.qnaId = qnaId;
        this.answer = answer;

        this.validateSelf();
    }
}
