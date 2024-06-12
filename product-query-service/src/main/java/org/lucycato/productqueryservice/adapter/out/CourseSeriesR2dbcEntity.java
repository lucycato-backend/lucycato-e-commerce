package org.lucycato.productqueryservice.adapter.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table("course_series")
@Getter
@Setter
@NoArgsConstructor
public class CourseSeriesR2dbcEntity {
    @Id
    private Long id;

    private Long teacherId;

    private String imageUrl;

    private String title;

    private String description;

    private List<String> explainImageUrls;

    private SubjectCategory subjectCategory;

    private CourseSeriesCategory category;

    private CourseSeriesStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
