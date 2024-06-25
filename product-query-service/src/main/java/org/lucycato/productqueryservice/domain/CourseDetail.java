package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.application.port.out.result.CourseDetailResult;
import org.lucycato.productqueryservice.domain.enums.CourseGenre;
import org.lucycato.productqueryservice.domain.enums.CourseStatus;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseDetail {
    private final Long courseId;

    private final Long courseSeriesId;

    private final String courseTitle;

    private final String courseSubTitle;

    private final Integer coursePrice;

    private final String courseImageUrl;

    private final String courseDescription;

    private final CourseGenre courseCourseGenre;

    private final SubjectCategory subjectCategory;

    private final CourseStatus courseStatus;

    private final Boolean isRecentCourseOpen;

    private final LocalDateTime courseExpiredAt;

    private final LocalDateTime courseCreatedAt;

    public static CourseDetail from(CourseDetailResult courseDetailResult, Boolean isRecentCourseOpen) {
        return CourseDetail.builder()
                .courseId(courseDetailResult.getCourseId())
                .courseSeriesId(courseDetailResult.getCourseSeriesId())
                .courseTitle(courseDetailResult.getCourseTitle())
                .courseSubTitle(courseDetailResult.getCourseSubTitle())
                .coursePrice(courseDetailResult.getCoursePrice())
                .courseImageUrl(courseDetailResult.getCourseImageUrl())
                .courseDescription(courseDetailResult.getCourseDescription())
                .courseCourseGenre(courseDetailResult.getCourseGenre())
                .subjectCategory(courseDetailResult.getSubjectCategory())
                .courseStatus(courseDetailResult.getCourseStatus())
                .isRecentCourseOpen(isRecentCourseOpen)
                .courseExpiredAt(courseDetailResult.getCourseExpiredAt())
                .courseCreatedAt(courseDetailResult.getCourseCreatedAt())
                .build();
    }
}
