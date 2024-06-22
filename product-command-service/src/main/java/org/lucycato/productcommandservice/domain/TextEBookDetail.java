package org.lucycato.productcommandservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productcommandservice.application.port.out.result.TextEBookDetailResult;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.lucycato.productcommandservice.domain.enums.TextEBookStatus;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextEBookDetail {
    private Long textEBookId;

    private Long courseId;

    private String textEBookUniqueCode;

    private String textEBookImageUrl;

    private String textEBookTitle;

    private String textEBookDescription;

    private String textEBookTableOfContents;

    private String textEBookAuthor;

    private String textEBookPublisher;

    private String textEBookPreviewDownloadUrl;

    private String textEBookFullDownloadUrl;

    private Integer textEBookPage;

    private SubjectCategory subjectCategory;

    private TextEBookStatus textEBookStatus;

    private LocalDateTime textEBookPublishedAt;

    private LocalDateTime textEBookCreatedAt;

    public static TextEBookDetail from(
        TextEBookDetailResult textEBookDetailResult
    ) {
        return TextEBookDetail.builder()
                .textEBookId(textEBookDetailResult.getTextEBookId())
                .courseId(textEBookDetailResult.getCourseId())
                .textEBookUniqueCode(textEBookDetailResult.getTextEBookUniqueCode())
                .textEBookImageUrl(textEBookDetailResult.getTextEBookImageUrl())
                .textEBookTitle(textEBookDetailResult.getTextEBookTitle())
                .textEBookDescription(textEBookDetailResult.getTextEBookDescription())
                .textEBookTableOfContents(textEBookDetailResult.getTextEBookTableOfContents())
                .textEBookAuthor(textEBookDetailResult.getTextEBookAuthor())
                .textEBookPublisher(textEBookDetailResult.getTextEBookPublisher())
                .textEBookPreviewDownloadUrl(textEBookDetailResult.getTextEBookPreviewDownloadUrl())
                .textEBookFullDownloadUrl(textEBookDetailResult.getTextEBookFullDownloadUrl())
                .textEBookPage(textEBookDetailResult.getTextEBookPage())
                .textEBookStatus(textEBookDetailResult.getTextEBookStatus())
                .textEBookPublishedAt(textEBookDetailResult.getTextEBookPublishedAt())
                .textEBookCreatedAt(textEBookDetailResult.getTextEBookCreatedAt())
                .build();
    }
}
