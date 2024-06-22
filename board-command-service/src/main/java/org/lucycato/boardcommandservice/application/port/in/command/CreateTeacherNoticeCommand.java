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
public class CreateTeacherNoticeCommand extends SelfValidating<CreateTeacherNoticeCommand> {

    @NotNull
    private Long teacherId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String type;

    public CreateTeacherNoticeCommand(Long teacherId, String content, String title, String type) {
        this.teacherId = teacherId;
        this.content = content;
        this.title = title;
        this.type = type;

        this.validateSelf();
    }
}
