package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productqueryservice.domain.enums.CourseGenre;
import org.lucycato.productqueryservice.domain.enums.CourseStatus;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailResult {
    private Long courseId;

    private Long teacherId;

    private Long courseSeriesId;

    private String courseTitle;

    private String courseSubTitle;

    private Integer coursePrice;

    private String courseImageUrl;

    private String courseDescription;

    private CourseGenre courseGenre;

    private SubjectCategory subjectCategory;

    private CourseStatus courseStatus;

    private LocalDateTime courseExpiredAt;

    private LocalDateTime courseCreatedAt;
}
