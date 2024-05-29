package org.lucycato.productservice.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productservice.domain.enums.LectureContentCategory;
import org.lucycato.productservice.domain.enums.LectureContentStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("lecture_content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureContentR2dbcEntity {
    @Id
    private Long id;

    private Long lectureId;

    private String title;

    private String description;

    private LectureContentCategory category;

    private String thumbnailImageUrl;

    private String videoUrl;

    private LectureContentStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public LectureContentR2dbcEntity(Long lectureId, String title, LectureContentCategory category) {
        this.lectureId = lectureId;
        this.title = title;
        this.category = category;
    }
}
