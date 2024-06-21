package org.lucycato.productcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.domain.enums.CourseGenre;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RegisterCourseRequest {
    private Long courseSeriesId;

    private String courseTitle;

    private String courseSubTitle;

    private Integer coursePrice;

    private String courseDescription;

    private CourseGenre courseGenre;

    private SubjectCategory subjectCategory;

    private LocalDateTime expiredAt;
}
