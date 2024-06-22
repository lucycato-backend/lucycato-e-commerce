package org.lucycato.productcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;

@Getter
@NoArgsConstructor
public class RegisterCourseSeriesRequest {
    private Long teacherId;

    private String courseSeriesTitle;

    private String courseSeriesDescription;

    private SubjectCategory subjectCategory;

    private CourseSeriesCategory courseSeriesCategory;
}
