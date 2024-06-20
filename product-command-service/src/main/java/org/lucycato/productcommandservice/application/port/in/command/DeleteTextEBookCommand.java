package org.lucycato.productcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DeleteTextEBookCommand extends SelfValidating<DeleteTextEBookCommand> {
    @NotNull
    private Long textEBookId;

    public DeleteTextEBookCommand(Long textEBookId) {
        this.textEBookId = textEBookId;

        this.validateSelf();
    }
}
