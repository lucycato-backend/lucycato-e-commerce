package com.lucycato.eventservice.application.port.in.commend;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddCSATGradeQueueCommend extends SelfValidating<AddCSATGradeQueueCommend> {
    @NotNull
    private Long appUserId;

    public AddCSATGradeQueueCommend(Long appUserId) {
        this.appUserId = appUserId;
    }
}
