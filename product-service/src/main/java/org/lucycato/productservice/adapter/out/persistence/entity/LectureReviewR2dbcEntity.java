package org.lucycato.productservice.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productservice.domain.enums.LectureReviewCategory;
import org.lucycato.productservice.domain.enums.LectureReviewStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table("lecture_review")
@Getter
@Setter
@NoArgsConstructor
public class LectureReviewR2dbcEntity {
    @Id
    private Long id;

    private Long appUserId;

    private Long teacherId;

    private Long lectureId;

    private Double star;

    private Integer viewCount;

    private String title;

    private String content;

    private LectureReviewCategory category;

    private LectureReviewStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
