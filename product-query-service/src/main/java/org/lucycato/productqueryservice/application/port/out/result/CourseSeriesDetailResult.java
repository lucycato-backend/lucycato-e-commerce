package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSeriesDetailResult {
    private Long courseSeriesId;

    private Long teacherId;

    private String courseSeriesImageUrl;

    private String courseSeriesTitle;

    private String courseSeriesDescription;

    private List<String> courseSeriesExplainImageUrls;

    private SubjectCategory subjectCategory;

    private CourseSeriesCategory courseSeriesCategory;

    private CourseSeriesStatus courseSeriesStatus;

    private LocalDateTime createdAt;
}
