package org.lucycato.productservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productservice.domain.enums.LectureSeriesCategory;

@Getter
@NoArgsConstructor
public class RegisteredLectureSeriesRequest {
    private Long teacherId;

    private String lectureSeriesTitle;

    private String lectureSeriesDescription;
}
