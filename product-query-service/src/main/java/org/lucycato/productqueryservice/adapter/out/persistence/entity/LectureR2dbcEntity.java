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

@Table("lecture")
@Getter
@Setter
@NoArgsConstructor
public class LectureR2dbcEntity {
    @Id
    private Long id;

    private Long courseId;

    private String title;

    private String description;

    private LectureCategory lectureCategory;

    private String thumbnailImageUrl;

    private String videoUrl;

    private LectureStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public LectureR2dbcEntity(Long courseId, String title, LectureCategory category) {
        this.courseId = courseId;
        this.title = title;
        this.lectureCategory = category;
    }
}
