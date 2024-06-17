package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckedRecentCourseOpenResult {
    private Long courseId;

    private Long teacherId;

    private Boolean isRecentCourseOpen;
}
