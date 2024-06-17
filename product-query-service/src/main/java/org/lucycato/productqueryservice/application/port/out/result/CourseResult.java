package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productqueryservice.domain.enums.CourseGenre;
import org.lucycato.productqueryservice.domain.enums.CourseStatus;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResult {
    private Long courseId;

    private Long teacherId;

    private String courseTitle;

    private String courseSubTitle;

    private Integer coursePrice;

    private String courseImageUrl;

    private CourseGenre courseGenre;

    private SubjectCategory subjectCategory;

    private CourseStatus courseStatus;

    private LocalDateTime expiredAt;

    private LocalDateTime createdAt;
}
