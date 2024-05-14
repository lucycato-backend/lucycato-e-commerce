package org.lucycato.productservice.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productservice.domain.enums.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table("lecture")
@Getter
@Setter
@NoArgsConstructor
public class LectureR2dbcEntity {
    @Id
    private Long id;

    private Long teacherId;

    private Long lectureSeriesId;

    private String lectureTitle;

    private String lectureSubTitle;

    private Integer lecturePrice;

    private String lectureImageUrl;

    private String lectureDescription;

    private String lectureComposition;

    private List<LectureTargetStudentCategory> lectureTargetStudentCategories;

    private LectureGenre lectureGenre;

    private TeachingGenre teachingGenre;

    private SubjectCategory subjectCategory;

    private LectureStatus status;

    private LocalDateTime expiredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
