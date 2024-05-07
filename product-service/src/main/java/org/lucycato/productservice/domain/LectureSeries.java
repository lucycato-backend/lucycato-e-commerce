package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.domain.enums.LectureSeriesCategory;
import org.lucycato.productservice.domain.enums.LectureSeriesStatus;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureSeries {
    private Long lectureSeriesId;

    private Long teacherId;

    private LectureSeriesCategory category;

    private String imageUrl;

    private String title;

    private String description;

    private LectureSeriesStatus status;
}
