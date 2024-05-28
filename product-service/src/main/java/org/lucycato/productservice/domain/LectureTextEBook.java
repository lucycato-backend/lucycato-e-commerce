package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.application.port.out.result.LectureTextEBookResult;
import org.lucycato.productservice.domain.enums.SubjectCategory;
import org.lucycato.productservice.domain.enums.TeachingGenre;
import org.lucycato.productservice.domain.enums.TextEBookStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureTextEBook {
    private final Long lectureTextEBookId;

    private final String lectureTextEBookUniqueCode;

    private final String lectureTextEBookTitle;

    private final String lectureTextEBookDescription;

    private final String lectureTextEBookTableOfContents;

    private final String lectureTextEBookAuthor;

    private final String lectureTextEBookPublisher;

    private final List<String> lectureTextEBookImageUrls;

    private final String lectureTextEBookPreviewDownloadUrl;

    private final String lectureTextEBookFullDownLoadUrl;

    private final Integer lectureTextEBookPage;

    private final SubjectCategory subjectCategory;

    private final TeachingGenre teachingGenre;

    private final TextEBookStatus textEBookStatus;

    private final LocalDateTime publishedAt;

    public static LectureTextEBook from(LectureTextEBookResult result) {
        return null;
    }

    public static Record createRecord() {
        return null;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Record {
        private final Long lectureTextEBookId;

        private final String lectureTextEBookUniqueCode;

        private final String lectureTextEBookTitle;

        private final String lectureTextEBookAuthor;

        private final List<String> lectureTextEBookImageUrls;

        private final String lectureTextEBookPreviewDownloadUrl;

        private final SubjectCategory subjectCategory;

        private final TeachingGenre teachingGenre;

        private final TextEBookStatus textEBookStatus;
    }
}
