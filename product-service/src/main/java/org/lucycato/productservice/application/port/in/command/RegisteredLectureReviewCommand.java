package org.lucycato.productservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisteredLectureReviewCommand extends SelfValidating<RegisteredLectureReviewCommand> {
    @NotNull
    private Long appUserId;

    @NotNull
    private Long lectureId;

    @NotBlank
    private String reviewTitle;

    @NotBlank
    private String reviewContent;

    public RegisteredLectureReviewCommand(Long appUserId, Long lectureId, String reviewTitle, String reviewContent) {
        this.appUserId = appUserId;
        this.lectureId = lectureId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;

        this.validateSelf();
    }
}
