package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.domain.enums.ReviewCategory;
import org.lucycato.productservice.domain.enums.ReviewStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureReview {
    private Long reviewId;

    private Long appUserId;

    private Long teacherId;

    private Long lectureId;

    private ReviewCategory category;

    private ReviewStatus status;

    private BigDecimal star;

    private Integer viewCount;

    private String title;

    private String content;

    private LocalDateTime createdAt;
}
