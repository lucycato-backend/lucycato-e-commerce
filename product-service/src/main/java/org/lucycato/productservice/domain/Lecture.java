package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.domain.enums.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lecture {
    private final Long lectureId;

    private final Teacher teacher;

    private final LectureSeries lectureSeries;

    private final List<LectureTextEBookPreView> lectureTextEBookPreViews;

    private final LecturePreViewUrl lecturePreViewUrl;

    private final String lectureTitle;

    private final String lectureSubTitle;

    private final Integer lecturePrice;

    private final String lectureImageUrl;

    private final String lectureDescription;

    private final String lectureComposition;

    private final List<LectureTargetStudentCategory> lectureTargetStudentCategories;

    private final LectureGenre lectureGenre;

    private final TeachingGenre teachingGenre;

    private final SubjectCategory subjectCategory;

    private final LectureStatus status;

    private final LocalDateTime expiredAt;

    private final LocalDateTime createdAt;

    public static Lecture from() {
        return null;
    }

    public static Record createRecord() {
        return null;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Record {
        private final Long lectureId;

        private final String teacherName;

        private final String lectureTitle;

        private final String lectureSubTitle;

        private final Integer lecturePrice;

        private final List<String> lectureEBookPreviews;

        private final List<String> lecturePreviewVideoUrls;

        private final List<LectureTargetStudentCategory> lectureTargetStudentCategories;

        private final LectureGenre lectureGenre;

        private final TeachingGenre teachingGenre;

        private final SubjectCategory subjectCategory;

        private final LectureStatus status;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Teacher {
        private final String teacherName;

        private final String teacherCurriculumImageUrl;

        private final String teacherCurriculumVideoUrl;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LectureSeries {
        private final String lectureSeriesTitle;

        private final String lectureSeriesDescription;

        private final LectureSeriesCategory lectureSeriesCategory;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LectureTextEBookPreView {
        private final Long lectureTextEBookId;

        private final String lectureTextEBookUniqueCode;

        private final String lectureTEBookTitle;

        private final String lectureTEBookPreviewDownLoadUrl;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LecturePreViewUrl {
        private final String otVideoUrl;

        private final String previewVideoUrl;
    }
}
