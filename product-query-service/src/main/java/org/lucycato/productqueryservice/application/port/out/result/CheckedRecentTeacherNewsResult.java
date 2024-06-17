package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckedRecentTeacherNewsResult {
    private Long newsId;

    private Long teacherId;

    private Boolean isRecentTeacherNews;
}
