package org.lucycato.productqueryservice.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.domain.enums.CourseReviewCategory;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseReview {
    private final Long reviewId;

    private final String reviewTitle;

    private final Double reviewStar;

    private final String reviewUserName;

    private final Integer reviewViewCount;

    private final CourseReviewCategory courseReviewCategory;

    private final LocalDateTime createdAt;
}
