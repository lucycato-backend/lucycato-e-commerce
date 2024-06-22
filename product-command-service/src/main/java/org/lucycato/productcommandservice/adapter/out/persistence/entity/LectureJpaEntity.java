package org.lucycato.productcommandservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucycato.productcommandservice.domain.enums.LectureCategory;
import org.lucycato.productcommandservice.domain.enums.LectureStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "lectures")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LectureJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseJpaEntity courseJpaEntity;

    @Column(length = 100, nullable = false)
    private String lectureTitle;

    @Column(length = 512, nullable = false)
    private String lectureDescription;

    @Column(length = 200, nullable = false)
    private String lectureThumbnailImageUrl;

    @Column(length = 200, nullable = false)
    private String lectureVideoUrl;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private LectureCategory lectureCategory;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private LectureStatus lectureStatus;

    @CreatedDate
    private LocalDateTime lectureCreatedAt;

    @LastModifiedDate
    private LocalDateTime lectureModifiedAt;

    public static LectureJpaEntity create(
            CourseJpaEntity courseJpaEntity,
            String lectureTitle,
            String lectureDescription,
            String lectureThumbnailImageUrl,
            String lectureVideoUrl,
            LectureCategory lectureCategory,
            LectureStatus lectureStatus
    ) {
        LectureJpaEntity lectureJpaEntity = LectureJpaEntity.builder()
                .courseJpaEntity(courseJpaEntity)
                .lectureTitle(lectureTitle)
                .lectureDescription(lectureDescription)
                .lectureThumbnailImageUrl(lectureThumbnailImageUrl)
                .lectureVideoUrl(lectureVideoUrl)
                .lectureCategory(lectureCategory)
                .lectureStatus(lectureStatus)
                .build();
        courseJpaEntity.addLecture(lectureJpaEntity);
        return lectureJpaEntity;
    }

    public static LectureJpaEntity create(
            Long courseId,
            String lectureTitle,
            String lectureDescription,
            String lectureThumbnailImageUrl,
            String lectureVideoUrl,
            LectureCategory lectureCategory,
            LectureStatus lectureStatus
    ) {
        return LectureJpaEntity.builder()
                .courseJpaEntity(CourseJpaEntity.builder().id(courseId).build())
                .lectureTitle(lectureTitle)
                .lectureDescription(lectureDescription)
                .lectureThumbnailImageUrl(lectureThumbnailImageUrl)
                .lectureVideoUrl(lectureVideoUrl)
                .lectureCategory(lectureCategory)
                .lectureStatus(lectureStatus)
                .build();
    }
}
