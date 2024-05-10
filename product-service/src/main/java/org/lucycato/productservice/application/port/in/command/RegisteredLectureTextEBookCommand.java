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
public class RegisteredLectureTextEBookCommand extends SelfValidating<RegisteredLectureTextEBookCommand> {
    @NotNull
    private Long lectureId;

    @NotBlank
    private String eBookUniqueCode;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String tableOfContents;

    @NotBlank
    private String author;

    @NotBlank
    private String publisher;

    @NotEmpty
    private Integer page;

    @NotEmpty
    private List<MultipartFile> lectureTextEBookImageFiles;

    @NotNull
    private MultipartFile previewTextEBookPDFFile;

    @NotNull
    private MultipartFile fullTextEBookPDFFile;

    public RegisteredLectureTextEBookCommand(Long lectureId, String eBookUniqueCode, String title, String description, String tableOfContents, String author, String publisher, Integer page, List<MultipartFile> lectureTextEBookImageFiles, MultipartFile previewTextEBookPDFFile, MultipartFile fullTextEBookPDFFile) {
        this.lectureId = lectureId;
        this.eBookUniqueCode = eBookUniqueCode;
        this.title = title;
        this.description = description;
        this.tableOfContents = tableOfContents;
        this.author = author;
        this.publisher = publisher;
        this.page = page;
        this.lectureTextEBookImageFiles = lectureTextEBookImageFiles;
        this.previewTextEBookPDFFile = previewTextEBookPDFFile;
        this.fullTextEBookPDFFile = fullTextEBookPDFFile;

        this.validateSelf();
    }
}
