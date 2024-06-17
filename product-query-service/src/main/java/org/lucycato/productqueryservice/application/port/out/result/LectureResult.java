package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productqueryservice.domain.enums.LectureCategory;
import org.lucycato.productqueryservice.domain.enums.LectureStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureResult {
    private Long lectureId;

    private String lectureTitle;

    private String lectureDescription;

    private LectureCategory lectureCategory;

    private String lectureVideoUrl;

    private LectureStatus lectureStatus;
}
