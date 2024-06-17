package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import org.lucycato.productqueryservice.domain.enums.TextEBookStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TextEBookResult {
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

    private Integer textEBookPage;

    private SubjectCategory subjectCategory;

    private TeachingGenre teachingGenre;

    private TextEBookStatus textEBookStatus;

    private LocalDateTime publishedAt;
}
