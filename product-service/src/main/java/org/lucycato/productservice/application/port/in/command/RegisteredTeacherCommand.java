package org.lucycato.productservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productservice.domain.enums.TeachingGenre;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisteredTeacherCommand extends SelfValidating<RegisteredTeacherCommand> {
    @NotBlank
    private String teacherName;

    private String slogan;

    private String profileDescription;

    @NotNull
    private TeachingGenre teachingGenre;

    private List<MultipartFile> teacherImageFiles;

    private MultipartFile curriculumImageFile;

    private MultipartFile curriculumVideoFile;

    public RegisteredTeacherCommand(String teacherName, String slogan, String profileDescription, TeachingGenre teachingGenre, List<MultipartFile> teacherImageFiles, MultipartFile curriculumImageFile, MultipartFile curriculumVideoFile) {
        this.teacherName = teacherName;
        this.slogan = slogan;
        this.profileDescription = profileDescription;
        this.teachingGenre = teachingGenre;
        this.teacherImageFiles = teacherImageFiles;
        this.curriculumImageFile = curriculumImageFile;
        this.curriculumVideoFile = curriculumVideoFile;

        this.validateSelf();
    }
}
