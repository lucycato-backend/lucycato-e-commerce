package org.lucycato.productservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productservice.domain.enums.LectureGenre;
import org.lucycato.productservice.domain.enums.LectureTargetStudentCategory;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisteredLectureCommand extends SelfValidating<RegisteredLectureCommand> {
    @NotNull
    private Long lectureSeriesId;

    @NotBlank
    private String lectureTitle;

    @NotBlank
    private String lectureSubTitle;

    @NotNull
    private Integer lecturePrice;

    @NotBlank
    private String lectureDescription;

    @NotBlank
    private String lectureComposition;

    @NotEmpty
    private List<LectureTargetStudentCategory> lectureTargetStudentCategories;

    @NotNull
    private LectureGenre lectureGenre;

    @NotNull
    private LocalDateTime expiredAt;

    private MultipartFile lectureImageFile;

    public RegisteredLectureCommand(Long lectureSeriesId, String lectureTitle, String lectureSubTitle, Integer lecturePrice, String lectureDescription, String lectureComposition, List<LectureTargetStudentCategory> lectureTargetStudentCategories, LectureGenre lectureGenre, LocalDateTime expiredAt, MultipartFile lectureImageFile) {
        this.lectureSeriesId = lectureSeriesId;
        this.lectureTitle = lectureTitle;
        this.lectureSubTitle = lectureSubTitle;
        this.lecturePrice = lecturePrice;
        this.lectureDescription = lectureDescription;
        this.lectureComposition = lectureComposition;
        this.lectureTargetStudentCategories = lectureTargetStudentCategories;
        this.lectureGenre = lectureGenre;
        this.expiredAt = expiredAt;
        this.lectureImageFile = lectureImageFile;

        this.validateSelf();
    }
}
