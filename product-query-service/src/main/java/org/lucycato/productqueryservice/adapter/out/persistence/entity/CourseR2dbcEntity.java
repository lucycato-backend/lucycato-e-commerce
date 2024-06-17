package org.lucycato.productqueryservice.adapter.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("course")
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

    private String title;

    private String subTitle;

    private Integer price;

    private String imageUrl;

    private String description;

    private String courseGenre;

    private String subjectCategory;

    private String courseStatus;

    private LocalDateTime expiredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
