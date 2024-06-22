package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesStatus;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseSeriesResult {
    private Long courseSeriesId;

    private Long teacherId;

    private String courseSeriesImageUrl;

    private String courseSeriesTitle;

    private String courseSeriesDescription;

    private CourseSeriesCategory courseSeriesCategory;

    private CourseSeriesStatus courseSeriesStatus;
}
