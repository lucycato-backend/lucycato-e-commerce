package org.lucycato.productqueryservice.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productqueryservice.domain.enums.LectureCategory;
import org.lucycato.productqueryservice.domain.enums.LectureStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("lectures")
@Getter
@Setter
@NoArgsConstructor
public class LectureR2dbcEntity {
    @Id
    private Long id;

    private Long courseId;

    private String lectureTitle;

    private String lectureDescription;

    private LectureCategory lectureCategory;

    private String lectureThumbnailImageUrl;

    private String lectureVideoUrl;

    private LectureStatus lectureStatus;

    @CreatedDate
    private LocalDateTime lectureCreatedAt;

    @LastModifiedDate
    private LocalDateTime lectureModifiedAt;
}
