package org.lucycato.productcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productcommandservice.domain.enums.CourseGenre;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisterCourseCommand extends SelfValidating<RegisterCourseCommand> {
    @NotNull
    private Long teacherId;

    @NotNull
    private Long courseSeriesId;

    @NotBlank
    private String courseTitle;

    @NotBlank
    private String courseSubTitle;

    @NotNull
    private Integer coursePrice;

    @NotBlank
    private String courseDescription;

    @NotNull
    private CourseGenre courseGenre;

    @NotNull
    private SubjectCategory subjectCategory;

    @NotNull
    private LocalDateTime expiredAt;

    private MultipartFile teacherImageFile;

    private MultipartFile teacherCurriculumImageFile;

    private MultipartFile teacherCurriculumVideoFile;

    public RegisterCourseCommand(Long teacherId, Long courseSeriesId, String courseTitle, String courseSubTitle, Integer coursePrice, String courseDescription, CourseGenre courseGenre, SubjectCategory subjectCategory, LocalDateTime expiredAt, MultipartFile teacherImageFile, MultipartFile teacherCurriculumImageFile, MultipartFile teacherCurriculumVideoFile) {
        this.teacherId = teacherId;
        this.courseSeriesId = courseSeriesId;
        this.courseTitle = courseTitle;
        this.courseSubTitle = courseSubTitle;
        this.coursePrice = coursePrice;
        this.courseDescription = courseDescription;
        this.courseGenre = courseGenre;
        this.subjectCategory = subjectCategory;
        this.expiredAt = expiredAt;
        this.teacherImageFile = teacherImageFile;
        this.teacherCurriculumImageFile = teacherCurriculumImageFile;
        this.teacherCurriculumVideoFile = teacherCurriculumVideoFile;

        this.validateSelf();
    }
}