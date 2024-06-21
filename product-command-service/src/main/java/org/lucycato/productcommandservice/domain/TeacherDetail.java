package org.lucycato.productcommandservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productcommandservice.application.port.out.result.TeacherDetailResult;
import org.lucycato.productcommandservice.domain.enums.TeacherStatus;
import org.lucycato.productcommandservice.domain.enums.TeachingGenre;

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

    private final Boolean isRecentTeacherNotice;

    private final TeachingGenre teachingGenre;

    private final TeacherStatus teacherStatus;

    private final LocalDateTime teacherCreatedAt;

    public static TeacherDetail from(
            TeacherDetailResult teacherDetailResult
    ) {
        Curriculum curriculum = Curriculum.builder()
                .curriculumImageUrl(teacherDetailResult.getCurriculumImageUrl())
                .curriculumVideoUrl(teacherDetailResult.getCurriculumVideoUrl())
                .build();

        Statistics statistics = Statistics.builder()
                .courseSeriesCount(0L)
                .allOperatorCourseCount(0L)
                .progressOperatorCourseCount(0L)
                .notOperatorCourseCount(0L)
                .operatorTextEBookCount(0L)
                .nonOperatorTextEBookCount(0L)
                .targetStudentReviewCount(0L)
                .teacherNoticeCount(0L)
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
                .isRecentCourseOpen(false)
                .isRecentTeacherNotice(false)
                .teachingGenre(teacherDetailResult.getTeachingGenre())
                .teacherStatus(teacherDetailResult.getTeacherStatus())
                .teacherCreatedAt(teacherDetailResult.getTeacherCreatedAt())
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
