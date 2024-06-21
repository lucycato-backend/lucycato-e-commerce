package org.lucycato.productcommandservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucycato.productcommandservice.domain.enums.CourseGenre;
import org.lucycato.productcommandservice.domain.enums.CourseStatus;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "courses")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CourseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_series_id")
    private CourseSeriesJpaEntity courseSeriesJpaEntity;

    @Column(length = 100, nullable = false)
    private String courseTitle;

    @Column(length = 100, nullable = false)
    private String courseSubTitle;

    @Column(nullable = false)
    private Integer coursePrice;

    @Column(length = 200, nullable = false)
    private String courseImageUrl;

    @Column(length = 1024, nullable = false)
    private String courseDescription;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseGenre courseGenre;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private SubjectCategory subjectCategory;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    private LocalDateTime courseExpiredAt;

    @CreatedDate
    private LocalDateTime courseCreatedAt;

    @LastModifiedDate
    private LocalDateTime courseModifiedAt;

    @OneToMany(mappedBy = "courseJpaEntity", fetch = FetchType.LAZY)
    private List<LectureJpaEntity> lectureJpaEntityList = List.of();

    @OneToMany(mappedBy = "courseJpaEntity", fetch = FetchType.LAZY)
    private List<TextEBookJpaEntity> textEBookJpaEntityList = List.of();

    public static CourseJpaEntity create(
            CourseSeriesJpaEntity courseSeriesJpaEntity,
            String courseTitle,
            String courseSubTitle,
            Integer coursePrice,
            String courseDescription,
            CourseGenre courseGenre,
            SubjectCategory subjectCategory,
            CourseStatus courseStatus,
            LocalDateTime courseExpiredAt
    ) {
        CourseJpaEntity courseJpaEntity = CourseJpaEntity.builder()
                .courseTitle(courseTitle)
                .courseSubTitle(courseSubTitle)
                .coursePrice(coursePrice)
                .courseDescription(courseDescription)
                .courseGenre(courseGenre)
                .subjectCategory(subjectCategory)
                .courseStatus(courseStatus)
                .courseExpiredAt(courseExpiredAt)
                .build();

        courseSeriesJpaEntity.addCourse(courseJpaEntity);
        return courseJpaEntity;
    }

    public static CourseJpaEntity create(
            Long courseSeriesId,
            String courseTitle,
            String courseSubTitle,
            Integer coursePrice,
            String courseDescription,
            CourseGenre courseGenre,
            SubjectCategory subjectCategory,
            CourseStatus courseStatus,
            LocalDateTime courseExpiredAt
    ) {
        return CourseJpaEntity.builder()
                .courseSeriesJpaEntity(CourseSeriesJpaEntity.builder().id(courseSeriesId).build())
                .courseTitle(courseTitle)
                .courseSubTitle(courseSubTitle)
                .coursePrice(coursePrice)
                .courseDescription(courseDescription)
                .courseGenre(courseGenre)
                .subjectCategory(subjectCategory)
                .courseStatus(courseStatus)
                .courseExpiredAt(courseExpiredAt)
                .build();
    }

    public void addLecture(LectureJpaEntity lectureJpaEntity) {
        lectureJpaEntityList.add(lectureJpaEntity);
    }

    public void addTextEBook(TextEBookJpaEntity textEBookJpaEntity) {
        textEBookJpaEntityList.add(textEBookJpaEntity);
    }
}
