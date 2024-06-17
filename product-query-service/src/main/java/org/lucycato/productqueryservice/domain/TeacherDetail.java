package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.application.port.out.result.CourseCountResult;
import org.lucycato.productqueryservice.application.port.out.result.TeacherDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookCountResult;
import org.lucycato.productqueryservice.domain.enums.TeacherStatus;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherDetail {
    private final Long teacherId;

    private final Integer teacherRank;

    private final String teacherName;

    private final String teacherSlogan;

    private final String teacherProfileDescription;

    private final String teacherImage;

    private final Curriculum curriculum;

    private final Statistics statistics;

    private final Boolean isRecentCourseOpen;

    private final Boolean isRecentTeacherNews;

    private final TeachingGenre teachingGenre;

    private final TeacherStatus teacherStatus;

    private final LocalDateTime createdAt;

    public static TeacherDetail from(
            TeacherDetailResult teacherDetailResult,
            Boolean isRecentCourseOpen,
            Boolean isRecentTeacherNews,
            Long courseSeriesCount,
            CourseCountResult courseCountResult,
            TextEBookCountResult textEBookCountResult,
            Long targetStudentReviewCount,
            Long teacherNewsCount
    ) {
        Curriculum curriculum = Curriculum.builder()
                .curriculumImageUrl(teacherDetailResult.getCurriculumImageUrl())
                .curriculumVideoUrl(teacherDetailResult.getCurriculumVideoUrl())
                .build();

        Statistics statistics = Statistics.builder()
                .courseSeriesCount(courseSeriesCount)
                .allOperatorCourseCount(courseCountResult.getAllOperatorCourseCount())
                .progressOperatorCourseCount(courseCountResult.getProgressOperatorCourseCount())
                .notOperatorCourseCount(courseCountResult.getNotOperatorCourseCount())
                .operatorTextEBookCount(textEBookCountResult.getOperatorTextEBookCount())
                .nonOperatorTextEBookCount(textEBookCountResult.getNonOperatorTextEBookCount())
                .targetStudentReviewCount(targetStudentReviewCount)
                .teacherNoticeCount(teacherNewsCount)
                .build();

        return TeacherDetail.builder()
                .teacherId(teacherDetailResult.getTeacherId())
                .teacherRank(teacherDetailResult.getTeacherRank())
                .teacherName(teacherDetailResult.getTeacherName())
                .teacherSlogan(teacherDetailResult.getTeacherSlogan())
                .teacherProfileDescription(teacherDetailResult.getTeacherProfileDescription())
                .teacherImage(teacherDetailResult.getTeacherImage())
                .curriculum(curriculum)
                .statistics(statistics)
                .isRecentCourseOpen(isRecentCourseOpen)
                .isRecentTeacherNews(isRecentTeacherNews)
                .teachingGenre(teacherDetailResult.getTeachingGenre())
                .teacherStatus(teacherDetailResult.getTeacherStatus())
                .createdAt(teacherDetailResult.getCreatedAt())
                .build();
    }

    public static TeacherDetail simple(
            TeacherDetailResult teacherDetailResult,
            Boolean isRecentCourseOpen,
            Boolean isRecentTeacherNews
    ) {
        Curriculum curriculum = Curriculum.builder()
                .curriculumImageUrl(teacherDetailResult.getCurriculumImageUrl())
                .curriculumVideoUrl(teacherDetailResult.getCurriculumVideoUrl())
                .build();

        return TeacherDetail.builder()
                .teacherId(teacherDetailResult.getTeacherId())
                .teacherRank(teacherDetailResult.getTeacherRank())
                .teacherName(teacherDetailResult.getTeacherName())
                .teacherSlogan(teacherDetailResult.getTeacherSlogan())
                .teacherProfileDescription(teacherDetailResult.getTeacherProfileDescription())
                .teacherImage(teacherDetailResult.getTeacherImage())
                .curriculum(curriculum)
                .isRecentCourseOpen(isRecentCourseOpen)
                .isRecentTeacherNews(isRecentTeacherNews)
                .teachingGenre(teacherDetailResult.getTeachingGenre())
                .teacherStatus(teacherDetailResult.getTeacherStatus())
                .createdAt(teacherDetailResult.getCreatedAt())
                .build();
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
        private final Long courseSeriesCount;

        private final Long allOperatorCourseCount;

        private final Long progressOperatorCourseCount;

        private final Long notOperatorCourseCount;

        private final Long operatorTextEBookCount;

        private final Long nonOperatorTextEBookCount;

        private final Long targetStudentReviewCount;

        private final Long teacherNoticeCount;
    }
}
