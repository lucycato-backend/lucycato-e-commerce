package org.lucycato.productcommandservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseSeriesDetail {
    private final Long courseSeriesId;

    private final Long teacherId;

    private final String courseSeriesImageUrl;

    private final String courseSeriesTitle;

    private final String courseSeriesDescription;

    private final List<String> courseSeriesExplainImageUrls;

    private final SubjectCategory subjectCategory;

    private final CourseSeriesCategory courseSeriesCategory;

    private final CourseSeriesStatus courseSeriesStatus;

    private final LocalDateTime courseSeriesCreatedAt;
}
