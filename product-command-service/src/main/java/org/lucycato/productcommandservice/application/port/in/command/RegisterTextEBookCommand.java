package org.lucycato.productcommandservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.lucycato.productcommandservice.domain.enums.TeachingGenre;
import org.lucycato.productcommandservice.domain.enums.TextEBookStatus;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisterTextEBookCommand extends SelfValidating<RegisterTextEBookCommand> {
    @NotNull
    private Long courseId;

    @NotBlank
    private String textEBookUniqueCode;

    @NotBlank
    private String textEBookTitle;

    @NotBlank
    private String textEBookDescription;

    @NotBlank
    private String textEBookTableOfContents;

    @NotBlank
    private String textEBookAuthor;

    @NotBlank
    private String textEBookPublisher;

    @NotNull
    private Integer textEBookPage;

    @NotNull
    private SubjectCategory subjectCategory;

    @NotNull
    private LocalDateTime textEBookPublishedAt;

    private MultipartFile textEBookImageFile;

    private MultipartFile textEBookPreviewDownloadFile;

    private MultipartFile textEBookFullDownloadUrl;

    public RegisterTextEBookCommand(Long courseId, String textEBookUniqueCode, String textEBookTitle, String textEBookDescription, String textEBookTableOfContents, String textEBookAuthor, String textEBookPublisher, Integer textEBookPage, SubjectCategory subjectCategory, LocalDateTime textEBookPublishedAt, MultipartFile textEBookImageFile, MultipartFile textEBookPreviewDownloadFile, MultipartFile textEBookFullDownloadUrl) {
        this.courseId = courseId;
        this.textEBookUniqueCode = textEBookUniqueCode;
        this.textEBookTitle = textEBookTitle;
        this.textEBookDescription = textEBookDescription;
        this.textEBookTableOfContents = textEBookTableOfContents;
        this.textEBookAuthor = textEBookAuthor;
        this.textEBookPublisher = textEBookPublisher;
        this.textEBookPage = textEBookPage;
        this.subjectCategory = subjectCategory;
        this.textEBookPublishedAt = textEBookPublishedAt;
        this.textEBookImageFile = textEBookImageFile;
        this.textEBookPreviewDownloadFile = textEBookPreviewDownloadFile;
        this.textEBookFullDownloadUrl = textEBookFullDownloadUrl;

        this.validateSelf();
    }
}
