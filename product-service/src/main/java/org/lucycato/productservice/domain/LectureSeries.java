package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.domain.enums.LectureSeriesCategory;
import org.lucycato.productservice.domain.enums.LectureSeriesStatus;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureSeries {
    private final Long lectureSeriesId;

    private final String lectureSeriesTitle;

    private final String lectureSeriesDescription;

    private final List<String> lectureSeriesExplainImageUrls;

    private final LectureSeriesCategory lectureSeriesCategory;

    private final LectureSeriesStatus lectureSeriesStatus;

    public static LectureSeries from() {
       return null;
    }

    public static Record createRecord() {
       return null;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Record {
        private final Long lectureSeriesId;

        private final String lectureSeriesTitle;

        private final LectureSeriesCategory lectureSeriesCategory;

        private final LectureSeriesStatus lectureSeriesStatus;
    }
}
