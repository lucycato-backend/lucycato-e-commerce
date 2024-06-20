package org.lucycato.productcommandservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productcommandservice.domain.enums.CourseGenre;
import org.lucycato.productcommandservice.domain.enums.CourseStatus;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseDetail {
    private final Long courseId;

    private final Long teacherId;

    private final Long courseSeriesId;

    private final String courseTitle;

    private final String courseSubTitle;

    private final Integer coursePrice;

    private final String courseImageUrl;

    private final String courseDescription;

    private final CourseGenre courseCourseGenre;

    private final SubjectCategory subjectCategory;

    private final CourseStatus courseStatus;

    private final LocalDateTime courseExpiredAt;

    private final LocalDateTime courseCreatedAt;
}
