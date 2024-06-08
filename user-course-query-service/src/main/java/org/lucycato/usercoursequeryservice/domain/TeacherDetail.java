package org.lucycato.usercoursequeryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.usercoursequeryservice.domain.enums.TeacherImageCategory;
import org.lucycato.usercoursequeryservice.domain.enums.TeacherStatus;
import org.lucycato.usercoursequeryservice.domain.enums.TeachingGenre;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherDetail {
    private final Long teacherId;

    private final Integer teacherRank;

    private final String teacherName;

    private final String teacherSlogan;

    private final String teacherProfileDescription;

    private final List<TeacherImage> teacherImages;

    private final Curriculum curriculum;

    private final Statistics statistics;

    private final Boolean isRecentCourseOpen;

    private final Boolean isRecentTeacherNews;

    private final TeachingGenre teachingGenre;

    private final TeacherStatus teacherStatus;

    private final LocalDateTime createdAt;

    public static TeacherDetail create() {
        return null;
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
