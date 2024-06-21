package org.lucycato.productcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productcommandservice.domain.enums.TeachingGenre;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ModifyTeacherCommand extends SelfValidating<ModifyTeacherCommand> {
    @NotNull
    private Long requestAdminUserId;

    @NotNull
    private Long teacherId;

    @NotNull
    private Integer teacherRank;

    @NotBlank
    private String teacherName;

    @NotBlank
    private String teacherSlogan;

    @NotBlank
    private String teacherProfileDescription;

    @NotNull
    private TeachingGenre teachingGenre;

    private MultipartFile teacherImageFile;

    private MultipartFile teacherCurriculumImageFile;

    private MultipartFile teacherCurriculumVideoFile;

    public ModifyTeacherCommand(Long requestAdminUserId, Long teacherId, Integer teacherRank, String teacherName, String teacherSlogan, String teacherProfileDescription, TeachingGenre teachingGenre, MultipartFile teacherImageFile, MultipartFile teacherCurriculumImageFile, MultipartFile teacherCurriculumVideoFile) {
        this.requestAdminUserId = requestAdminUserId;
        this.teacherId = teacherId;
        this.teacherRank = teacherRank;
        this.teacherName = teacherName;
        this.teacherSlogan = teacherSlogan;
        this.teacherProfileDescription = teacherProfileDescription;
        this.teachingGenre = teachingGenre;
        this.teacherImageFile = teacherImageFile;
        this.teacherCurriculumImageFile = teacherCurriculumImageFile;
        this.teacherCurriculumVideoFile = teacherCurriculumVideoFile;

        this.validateSelf();
    }
}
