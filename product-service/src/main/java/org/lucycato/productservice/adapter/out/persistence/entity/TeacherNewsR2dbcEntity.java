package org.lucycato.productservice.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productservice.domain.enums.TeacherNewStatus;
import org.lucycato.productservice.domain.enums.TeacherNewssCategory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("teacher_news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherNewsR2dbcEntity {
    @Id
    private Long id;

    private Long teacherId;

    private String title;

    private String content;

    private Integer viewCount;

    private TeacherNewssCategory category;

    private TeacherNewStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
