package org.lucycato.productcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ModifyCourseSeriesCommand extends SelfValidating<ModifyCourseSeriesCommand> {
    @NotNull
    private Long courseSeriesId;

    @NotNull
    private Long teacherId;

    @NotBlank
    private String courseSeriesTitle;

    @NotBlank
    private String courseSeriesDescription;

    @NotNull
    private SubjectCategory subjectCategory;

    @NotNull
    private CourseSeriesCategory courseSeriesCategory;

    private MultipartFile courseSeriesImageFile;

    private List<MultipartFile> courseSeriesExplainImageFiles;

    public ModifyCourseSeriesCommand(Long courseSeriesId, Long teacherId, String courseSeriesTitle, String courseSeriesDescription, SubjectCategory subjectCategory, CourseSeriesCategory courseSeriesCategory, MultipartFile courseSeriesImageFile, List<MultipartFile> courseSeriesExplainImageFiles) {
        this.courseSeriesId = courseSeriesId;
        this.teacherId = teacherId;
        this.courseSeriesTitle = courseSeriesTitle;
        this.courseSeriesDescription = courseSeriesDescription;
        this.subjectCategory = subjectCategory;
        this.courseSeriesCategory = courseSeriesCategory;
        this.courseSeriesImageFile = courseSeriesImageFile;
        this.courseSeriesExplainImageFiles = courseSeriesExplainImageFiles;

        this.validateSelf();
    }
}
