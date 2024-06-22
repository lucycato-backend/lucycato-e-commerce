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
public class CreateMainBoardCommand extends SelfValidating<CreateMainBoardCommand> {

    @NotNull
    private Long userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String type;

    public CreateMainBoardCommand(Long userId, String title, String content, String type) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.type = type;

        this.validateSelf();
    }

}
