package org.lucycato.usercoursequeryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.usercoursequeryservice.domain.enums.SubjectCategory;
import org.lucycato.usercoursequeryservice.domain.enums.TeachingGenre;
import org.lucycato.usercoursequeryservice.domain.enums.TextEBookStatus;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseTextEBook {
    private final Long textEBookId;

    private final String textEBookUniqueCode;

    private final String textEBookImageUrl;

    private final String textEBookTitle;

    private final String textEBookDescription;

    private final String textEBookTableOfContents;

    private final String textEBookAuthor;

    private final String textEBookPublisher;

    private final String textEBookPreviewDownloadUrl;

    private final Integer textEBookPage;

    private final SubjectCategory subjectCategory;

    private final TeachingGenre teachingGenre;

    private final TextEBookStatus textEBookStatus;

    private final LocalDateTime publishedAt;
}
