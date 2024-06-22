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
public class CreateCourseReviewCommand extends SelfValidating<CreateCourseReviewCommand> {

    @NotNull
    private Long id;

    @NotNull
    private Long teacherId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private int score;

    @NotNull
    private Long lectureId;

    public CreateCourseReviewCommand(Long id, Long teacherId, Long lectureId, String title, String content, int score) {
        this.id = id;
        this.teacherId = teacherId;
        this.lectureId = lectureId;
        this.title = title;
        this.content = content;
        this.score = score;

        this.validateSelf();
    }
}
