package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.domain.enums.LectureContentCategory;
import org.lucycato.productservice.domain.enums.LectureContentStatus;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureContent {
    private final Long lectureContentId;

    private final String lectureContentTitle;

    private final LectureContentCategory lectureContentCategory;

    private final String lectureContentThumbnailImageUrl;

    private final String lectureContentVideoUrl;

    private final LectureContentStatus lectureContentStatus;

    private final LocalDateTime createdAt;

    public static LectureContent from() {
       return null;
    }

    public static Record createRecord() {
        return null;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Record {
        private final Long lectureContentId;

        private final String lectureContentTitle;

        private final LectureContentStatus lectureContentStatus;

        private final LocalDateTime createdAt;
    }
}
