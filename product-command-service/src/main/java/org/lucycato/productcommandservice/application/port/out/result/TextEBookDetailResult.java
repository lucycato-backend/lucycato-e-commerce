package org.lucycato.productcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.TextEBookJpaEntity;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.lucycato.productcommandservice.domain.enums.TextEBookStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TextEBookDetailResult {
    private Long textEBookId;

    private Long courseId;

    private String textEBookUniqueCode;

    private String textEBookImageUrl;

    private String textEBookPreviewDownloadUrl;

    private String textEBookFullDownloadUrl;

    private String textEBookTitle;

    private String textEBookDescription;

    private String textEBookTableOfContents;

    private String textEBookAuthor;

    private String textEBookPublisher;

    private Integer textEBookPage;

    private SubjectCategory subjectCategory;

    private TextEBookStatus textEBookStatus;

    private LocalDateTime textEBookPublishedAt;

    private LocalDateTime textEBookCreatedAt;

    public static TextEBookDetailResult from(TextEBookJpaEntity textEBookJpaEntity) {
        return TextEBookDetailResult.builder()
                .textEBookId(textEBookJpaEntity.getId())
                .courseId(textEBookJpaEntity.getCourseJpaEntity().getId())
                .textEBookImageUrl(textEBookJpaEntity.getTextEBookImageUrl())
                .textEBookPreviewDownloadUrl(textEBookJpaEntity.getTextEBookPreviewDownloadUrl())
                .textEBookFullDownloadUrl(textEBookJpaEntity.getTextEBookFullDownloadUrl())
                .textEBookTitle(textEBookJpaEntity.getTextEBookTitle())
                .textEBookDescription(textEBookJpaEntity.getTextEBookDescription())
                .textEBookTableOfContents(textEBookJpaEntity.getTextEBookTableOfContents())
                .textEBookAuthor(textEBookJpaEntity.getTextEBookAuthor())
                .textEBookPreviewDownloadUrl(textEBookJpaEntity.getTextEBookPublisher())
                .textEBookPage(textEBookJpaEntity.getTextEBookPage())
                .subjectCategory(textEBookJpaEntity.getSubjectCategory())
                .textEBookStatus(textEBookJpaEntity.getTextEBookStatus())
                .textEBookPublishedAt(textEBookJpaEntity.getTextEBookPublishedAt())
                .textEBookCreatedAt(textEBookJpaEntity.getTextEBookCreatedAt())
                .build();
    }
}
