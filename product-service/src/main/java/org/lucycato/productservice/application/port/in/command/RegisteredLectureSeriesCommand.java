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
public class RegisteredLectureSeriesCommand extends SelfValidating<RegisteredLectureSeriesCommand> {
    @NotNull
    private Long teacherId;

    @NotBlank
    private String lectureSeriesTitle;

    @NotBlank
    private String lectureSeriesDescription;

    private List<MultipartFile> lectureSeriesExplainImageFiles;

    public RegisteredLectureSeriesCommand(Long teacherId, String lectureSeriesTitle, String lectureSeriesDescription, List<MultipartFile> lectureSeriesExplainImageFiles) {
        this.teacherId = teacherId;
        this.lectureSeriesTitle = lectureSeriesTitle;
        this.lectureSeriesDescription = lectureSeriesDescription;
        this.lectureSeriesExplainImageFiles = lectureSeriesExplainImageFiles;

        this.validateSelf();
    }
}
