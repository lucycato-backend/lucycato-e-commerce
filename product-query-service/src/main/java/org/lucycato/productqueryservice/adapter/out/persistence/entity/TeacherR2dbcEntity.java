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

    private Integer teacherRank;

    private String teacherName;

    private String teacherSlogan;

    private String teacherProfileDescription;

    private String teacherImageUrl;

    private String teacherCurriculumImageUrl;

    private String teacherCurriculumVideoUrl;

    private TeachingGenre teachingGenre;

    private TeacherStatus teacherStatus;

    @CreatedDate
    private LocalDateTime teacherCreatedAt;

    @LastModifiedDate
    private LocalDateTime teacherModifiedAt;
}
