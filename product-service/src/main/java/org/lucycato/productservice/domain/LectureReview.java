package org.lucycato.productservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productservice.domain.enums.LectureReviewCategory;
import org.lucycato.productservice.domain.enums.LectureReviewStatus;
import org.lucycato.productservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureReview {
    private final Long lectureReviewId;

    private final String teacherName;

    private final String safeUserName;

    private final Lecture lecture;

    private final Double lectureReviewStar;

    private final Integer lectureReviewViewCount;

    private final String lectureReviewTitle;

    private final String lectureReviewContent;

    private final SubjectCategory subjectCategory;

    private final LectureReviewCategory lectureReviewCategory;

    private final LectureReviewStatus lectureReviewStatus;

    private final LocalDateTime createdAt;

    public static LectureReview from() {
        return null;
    }

    public static Record creatRecord() {
        return null;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Record {
        private final Long lectureReViewId;

        private final String safeUserName;

        private final String lectureReviewTitle;

        private final Double lectureReviewStar;

        private final Integer lectureReviewViewCount;

        private final LectureReviewCategory lectureReviewCategory;

        private final LectureReviewStatus lectureReviewStatus;

        private final LocalDateTime createdAt;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Lecture {
        private final String lectureName;

        private final String lectureTarget;

        private final String lectureOTVideoUrl;

        private final String lecturePreviewVideoUrl;
    }
}
