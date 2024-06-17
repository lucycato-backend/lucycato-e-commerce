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

    private final Long teacherId;

    private final Long courseSeriesId;

    private final String title;

    private final String subTitle;

    private final Integer price;

    private final String imageUrl;

    private final String description;

    private final CourseGenre courseGenre;

    private final SubjectCategory subjectCategory;

    private final CourseStatus courseStatus;

    private final LocalDateTime expiredAt;

    private final LocalDateTime createdAt;

    public static CourseDetail from(CourseDetailResult courseDetailResult) {
        return CourseDetail.builder()
                .courseId(courseDetailResult.getCourseId())
                .teacherId(courseDetailResult.getTeacherId())
                .courseSeriesId(courseDetailResult.getCourseSeriesId())
                .title(courseDetailResult.getTitle())
                .subTitle(courseDetailResult.getSubTitle())
                .price(courseDetailResult.getPrice())
                .imageUrl(courseDetailResult.getImageUrl())
                .description(courseDetailResult.getDescription())
                .courseGenre(courseDetailResult.getCourseGenre())
                .subjectCategory(courseDetailResult.getSubjectCategory())
                .courseStatus(courseDetailResult.getCourseStatus())
                .expiredAt(courseDetailResult.getExpiredAt())
                .createdAt(courseDetailResult.getCreatedAt())
                .build();
    }
}
