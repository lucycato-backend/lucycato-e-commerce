package org.lucycato.productqueryservice.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("courses")
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseR2dbcEntity {
    @Id
    private Long id;

    private Long teacherId;

    private Long courseSeriesId;

    private String courseTitle;

    private String courseSubTitle;

    private Integer coursePrice;

    private String courseImageUrl;

    private String courseDescription;

    private String courseGenre;

    private String subjectCategory;

    private String courseStatus;

    private LocalDateTime courseExpiredAt;

    @CreatedDate
    private LocalDateTime courseCreatedAt;

    @LastModifiedDate
    private LocalDateTime courseModifiedAt;
}
