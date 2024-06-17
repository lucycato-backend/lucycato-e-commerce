package org.lucycato.productqueryservice.application.port.out.result;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckedRecentCourseOpenResult {
    private Long courseId;

    private Long teacherId;

    private Boolean isRecentCourseOpen;

    public CheckedRecentCourseOpenResult updateIsRecentCourseOpenTrue() {
        this.isRecentCourseOpen = true;
        return this;
    }
}
