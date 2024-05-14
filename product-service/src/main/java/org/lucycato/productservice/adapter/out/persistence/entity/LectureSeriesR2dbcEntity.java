package org.lucycato.productservice.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productservice.adapter.out.persistence.vo.LectureSeriesImageVo;
import org.lucycato.productservice.domain.enums.LectureSeriesCategory;
import org.lucycato.productservice.domain.enums.LectureSeriesStatus;
import org.lucycato.productservice.domain.enums.SubjectCategory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table("lecture_series")
@Getter
@Setter
@NoArgsConstructor
public class LectureSeriesR2dbcEntity {
    @Id
    private Long id;

    private Long teacherId;

    private LectureSeriesCategory category;

    private List<LectureSeriesImageVo> lectureSeriesExplainImageUrls;

    private String title;

    private String description;

    private SubjectCategory subjectCategory;

    private LectureSeriesStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
