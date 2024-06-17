package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCountResult {
    private Integer allOperatorCourseCount;

    private Integer completeOperatorCourseCount;

    private Integer progressOperatorCourseCount;

    private Integer notOperatorCourseCount;
}
