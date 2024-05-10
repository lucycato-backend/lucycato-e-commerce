package org.lucycato.productservice.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ByIdCommand extends SelfValidating<ByIdCommand> {
    @NotNull
    private Long targetId;

    public ByIdCommand(Long targetId) {
        this.targetId = targetId;

        this.validateSelf();
    }
}
