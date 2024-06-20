package org.lucycato.productcommandservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseJpaEntity;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.lucycato.productcommandservice.domain.enums.TextEBookStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
}
