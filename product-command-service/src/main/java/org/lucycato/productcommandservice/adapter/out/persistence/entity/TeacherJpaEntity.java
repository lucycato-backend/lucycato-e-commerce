package org.lucycato.productcommandservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucycato.productcommandservice.domain.enums.TeacherStatus;
import org.lucycato.productcommandservice.domain.enums.TeachingGenre;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "teachers")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TeacherJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer teacherRank;

    @Column(length = 50, nullable = false)
    private String teacherName;

    @Column(length = 100, nullable = false)
    private String teacherSlogan;

    @Column(length = 1024, nullable = false)
    private String teacherProfileDescription;

    @Column(length = 200, nullable = false)
    private String teacherImageUrl;

    @Column(length = 200, nullable = false)
    private String teacherCurriculumImageUrl;

    @Column(length = 200, nullable = false)
    private String teacherCurriculumVideoUrl;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private TeachingGenre teachingGenre;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private TeacherStatus teacherStatus;

    @CreatedDate
    private LocalDateTime teacherCreatedAt;

    @LastModifiedDate
    private LocalDateTime teacherModifiedAt;

    @OneToMany(mappedBy = "teacherJpaEntity", fetch = FetchType.LAZY)
    List<CourseSeriesJpaEntity> courseSeriesJpaEntityList = List.of();

    public static TeacherJpaEntity create(
            Integer teacherRank,
            String teacherName,
            String teacherSlogan,
            String teacherProfileDescription,
            String teacherImageUrl,
            String teacherCurriculumImageUrl,
            String teacherCurriculumVideoUrl,
            TeachingGenre teachingGenre,
            TeacherStatus teacherStatus
    ) {
        return TeacherJpaEntity.builder()
                .teacherRank(teacherRank)
                .teacherName(teacherName)
                .teacherSlogan(teacherSlogan)
                .teacherProfileDescription(teacherProfileDescription)
                .teacherImageUrl(teacherImageUrl)
                .teacherCurriculumImageUrl(teacherCurriculumImageUrl)
                .teacherCurriculumVideoUrl(teacherCurriculumVideoUrl)
                .teachingGenre(teachingGenre)
                .teacherStatus(teacherStatus)
                .build();
    }

    public void addCourseSeries(CourseSeriesJpaEntity courseSeriesJpaEntity) {
        courseSeriesJpaEntityList.add(courseSeriesJpaEntity);
    }
}
