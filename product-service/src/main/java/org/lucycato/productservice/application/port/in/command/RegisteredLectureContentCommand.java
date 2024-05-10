package org.lucycato.productservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productservice.domain.enums.LectureContentCategory;
import org.lucycato.productservice.domain.enums.LectureGenre;
import org.lucycato.productservice.domain.enums.LectureTargetStudentCategory;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisteredLectureContentCommand extends SelfValidating<RegisteredLectureContentCommand> {
    @NotNull
    private Long lectureId;

    @NotBlank
    private String lectureContentTitle;

    @NotNull
    private LectureContentCategory lectureContentCategory;

    @NotNull
    private MultipartFile lectureContentThumbnailImageFile;

    @NotNull
    private MultipartFile lectureContentVideoUrl;

    public RegisteredLectureContentCommand(Long lectureId, String lectureContentTitle, LectureContentCategory lectureContentCategory, MultipartFile lectureContentThumbnailImageFile, MultipartFile lectureContentVideoUrl) {
        this.lectureId = lectureId;
        this.lectureContentTitle = lectureContentTitle;
        this.lectureContentCategory = lectureContentCategory;
        this.lectureContentThumbnailImageFile = lectureContentThumbnailImageFile;
        this.lectureContentVideoUrl = lectureContentVideoUrl;

        this.validateSelf();
    }
}
