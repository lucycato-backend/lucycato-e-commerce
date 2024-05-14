package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.application.port.out.result.TeacherResult;
import org.lucycato.productservice.domain.enums.TeacherImageCategory;
import org.lucycato.productservice.domain.enums.TeachingGenre;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Teacher {
    private final Long teacherId;

    private final Integer teacherRank;

    private final String teacherName;

    private final String teacherSlogan;

    private final String teacherProfileDescription;

    private final List<TeacherImage> teacherImages;

    private final Curriculum curriculum;

    private final Statistics statistics;

    private final Boolean isRecentLectureOpen;

    private final Boolean isRecentTeacherNews;

    private final TeachingGenre teachingGenre;

    private final LocalDateTime createdAt;

    public static Teacher from(TeacherResult result) {
        return null;
    }

    public static Record createRecord() {
        return null;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Record {
        private final Long teacherId;

        private final String teacherRank;

        private final String teacherName;

        private final String teacherSlogan;

        private final List<TeacherImage> teacherImages;

        private final TeachingGenre teachingGenre;

        private final Boolean isRecentLectureOpen;

        private final Boolean isRecentTeacherNews;

        private final LocalDateTime createdAt;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TeacherImage {
        private final TeacherImageCategory teacherImageCategory;

        private final String teacherImageUrl;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Curriculum {
        private final String curriculumImageUrl;

        private final String curriculumVideoUrl;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Statistics {
        private final Integer lectureSeriesCount;

        private final Integer allOperatorLectureCount;

        private final Integer completeOperatorLectureCount;

        private final Integer progressOperatorLectureCount;

        private final Integer notOperatorLectureCount;

        private final Integer operatorTextEBookCount;

        private final Integer nonOperatorTextEBookCount;

        private final Integer targetStudentReviewCount;

        private final Integer teacherNewsCount;
    }
}
