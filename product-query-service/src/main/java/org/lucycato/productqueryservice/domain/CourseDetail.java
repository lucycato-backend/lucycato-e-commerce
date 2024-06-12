package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.domain.enums.CourseGenre;
import org.lucycato.productqueryservice.domain.enums.CourseStatus;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseDetail {
    private final Long id;

    private final Long teacherId;

    private final Long courseSeriesId;

    private final String title;

    private final String subTitle;

    private final Integer price;

    private final String imageUrl;

    private final String description;

    private final CourseGenre courseGenre;

    private final SubjectCategory subjectCategory;

    private final CourseStatus courseStatus;

    private final LocalDateTime expiredAt;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;
}
