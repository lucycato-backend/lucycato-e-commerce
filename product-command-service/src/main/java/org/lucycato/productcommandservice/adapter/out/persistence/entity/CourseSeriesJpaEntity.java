package org.lucycato.productcommandservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucycato.productcommandservice.adapter.out.persistence.converter.StringListConverter;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "course_series")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CourseSeriesJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherJpaEntity teacherJpaEntity;

    @Column(length = 200, nullable = false)
    private String courseSeriesImageUrl;

    @Column(length = 100, nullable = false)
    private String courseSeriesTitle;

    @Column(length = 512, nullable = false)
    private String courseSeriesDescription;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "JSON")
    private List<String> courseSeriesExplainImageUrlsJson = List.of();

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private SubjectCategory subjectCategory;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseSeriesCategory courseSeriesCategory;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseSeriesStatus courseSeriesStatus;

    @CreatedDate
    private LocalDateTime courseSeriesCreatedAt;

    @LastModifiedDate
    private LocalDateTime courseSeriesModifiedAt;

    @OneToMany(mappedBy = "courseSeriesJpaEntity", fetch = FetchType.LAZY)
    private List<CourseJpaEntity> courseJpaEntityList = List.of();

    public static CourseSeriesJpaEntity create(
            TeacherJpaEntity teacherJpaEntity,
            String courseSeriesImageUrl,
            String courseSeriesTitle,
            String courseSeriesDescription,
            List<String> courseSeriesExplainImageUrlsJson,
            SubjectCategory subjectCategory,
            CourseSeriesCategory courseSeriesCategory,
            CourseSeriesStatus courseSeriesStatus
    ) {
        CourseSeriesJpaEntity courseSeriesJpaEntity =  CourseSeriesJpaEntity.builder()
                .teacherJpaEntity(teacherJpaEntity)
                .courseSeriesImageUrl(courseSeriesImageUrl)
                .courseSeriesTitle(courseSeriesTitle)
                .courseSeriesDescription(courseSeriesDescription)
                .courseSeriesExplainImageUrlsJson(courseSeriesExplainImageUrlsJson)
                .subjectCategory(subjectCategory)
                .courseSeriesCategory(courseSeriesCategory)
                .courseSeriesStatus(courseSeriesStatus)
                .build();
        teacherJpaEntity.addCourseSeries(courseSeriesJpaEntity);
        return courseSeriesJpaEntity;
    }

    public static CourseSeriesJpaEntity create(
            Long teacherId,
            String courseSeriesImageUrl,
            String courseSeriesTitle,
            String courseSeriesDescription,
            List<String> courseSeriesExplainImageUrlsJson,
            SubjectCategory subjectCategory,
            CourseSeriesCategory courseSeriesCategory,
            CourseSeriesStatus courseSeriesStatus
    ) {
        return CourseSeriesJpaEntity.builder()
                .teacherJpaEntity(TeacherJpaEntity.builder().id(teacherId).build())
                .courseSeriesImageUrl(courseSeriesImageUrl)
                .courseSeriesTitle(courseSeriesTitle)
                .courseSeriesDescription(courseSeriesDescription)
                .courseSeriesExplainImageUrlsJson(courseSeriesExplainImageUrlsJson)
                .subjectCategory(subjectCategory)
                .courseSeriesCategory(courseSeriesCategory)
                .courseSeriesStatus(courseSeriesStatus)
                .build();
    }

    public void addCourse(CourseJpaEntity courseJpaEntity) {
        courseJpaEntityList.add(courseJpaEntity);
    }
}
