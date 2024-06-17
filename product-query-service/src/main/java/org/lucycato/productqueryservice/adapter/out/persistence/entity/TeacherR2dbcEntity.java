package org.lucycato.productqueryservice.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productqueryservice.domain.enums.TeacherStatus;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("teachers")
@Getter
@Setter
@NoArgsConstructor
public class TeacherR2dbcEntity {
    @Id
    private Long id;

    private Integer rank;

    private String name;

    private String slogan;

    private String profileDescription;

    private String imageUrl;

    private String curriculumImageUrl;

    private String curriculumVideoUrl;

    private TeachingGenre genre;

    private TeacherStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
