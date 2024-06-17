package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesDetailResult;
import org.lucycato.productqueryservice.domain.enums.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseSeriesDetail {
    private final Long courseSeriesId;

    private final Long teacherId;

    private final String courseSeriesImageUrl;

    private final String courseSeriesTitle;

    private final String courseSeriesDescription;

    private final List<String> courseSeriesExplainImageUrls;

    private final SubjectCategory subjectCategory;

    private final CourseSeriesCategory courseSeriesCategory;

    private final CourseSeriesStatus courseSeriesStatus;

    private final LocalDateTime createdAt;

    public static CourseSeriesDetail from(CourseSeriesDetailResult result) {
        return CourseSeriesDetail.builder()
                .courseSeriesId(result.getCourseSeriesId())
                .teacherId(result.getTeacherId())
                .courseSeriesImageUrl(result.getCourseSeriesImageUrl())
                .courseSeriesTitle(result.getCourseSeriesDescription())
                .courseSeriesDescription(result.getCourseSeriesTitle())
                .subjectCategory(result.getSubjectCategory())
                .courseSeriesCategory(result.getCourseSeriesCategory())
                .courseSeriesStatus(result.getCourseSeriesStatus())
                .createdAt(result.getCreatedAt())
                .build();
    }
}
