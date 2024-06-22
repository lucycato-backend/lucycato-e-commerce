package org.lucycato.productcommandservice.application.port.out;

import org.lucycato.productcommandservice.application.port.out.result.TextEBookDetailResult;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.lucycato.productcommandservice.domain.enums.TextEBookStatus;

import java.time.LocalDateTime;

public interface TextEBookPort {

    TextEBookDetailResult registerTextEBook(
            Long courseId,
            String textEBookUniqueCode,
            String textEBookImageUrl,
            String textEBookTitle,
            String textEBookDescription,
            String textEBookTableOfContents,
            String textEBookAuthor,
            String textEBookPublisher,
            String textEBookPreviewDownloadUrl,
            String textEBookFullDownloadUrl,
            Integer textEBookPage,
            SubjectCategory subjectCategory,
            TextEBookStatus textEBookStatus,
            LocalDateTime textEBookPublishedAt
    );

    TextEBookDetailResult modifyTextEBook(
            Long textEBookId,
            Long courseId,
            String textEBookUniqueCode,
            String textEBookImageUrl,
            String textEBookTitle,
            String textEBookDescription,
            String textEBookTableOfContents,
            String textEBookAuthor,
            String textEBookPublisher,
            String textEBookPreviewDownloadUrl,
            String textEBookFullDownloadUrl,
            Integer textEBookPage,
            SubjectCategory subjectCategory,
            LocalDateTime textEBookPublishedAt
    );

    void removeTextEBook(Long textEBook);
}
