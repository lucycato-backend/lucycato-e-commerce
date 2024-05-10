package org.lucycato.productservice.application.port.in.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ByIdsCommand extends SelfValidating<ByIdsCommand> {
    @NotEmpty
    private List<Long> targetIds;

    public ByIdsCommand(List<Long> targetIds) {
        this.targetIds = targetIds;

        this.validateSelf();
    }
}
