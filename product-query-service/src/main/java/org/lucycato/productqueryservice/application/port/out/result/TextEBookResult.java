package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
import org.lucycato.productqueryservice.domain.enums.TextEBookStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TextEBookResult {
    private Long textEBookId;

    private Long courseId;

    private Long courseSeriesId;

    private String textEBookUniqueCode;

    private String textEBookImageUrl;

    private String textEBookTitle;

    private String textEBookDescription;

    private String textEBookTableOfContents;

    private String textEBookAuthor;

    private String textEBookPublisher;

    private String textEBookPreviewDownloadUrl;

    private Integer textEBookPage;

    private SubjectCategory subjectCategory;

    private TextEBookStatus textEBookStatus;

    private LocalDateTime textEBookPublishedAt;
}
