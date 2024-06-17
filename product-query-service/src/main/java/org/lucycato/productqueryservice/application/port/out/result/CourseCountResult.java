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
    private Long allOperatorCourseCount;

    private Long progressOperatorCourseCount;

    private Long notOperatorCourseCount;
}
