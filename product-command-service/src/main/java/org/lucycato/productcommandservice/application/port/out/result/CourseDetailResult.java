package org.lucycato.productcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseJpaEntity;
import org.lucycato.productcommandservice.domain.enums.CourseGenre;
import org.lucycato.productcommandservice.domain.enums.CourseStatus;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailResult {
    private Long courseId;

    private Long courseSeriesId;

    private String courseImageUrl;

    private String courseTitle;

    private String courseSubTitle;

    private Integer coursePrice;

    private String courseDescription;

    private CourseGenre courseGenre;

    private SubjectCategory subjectCategory;

    private CourseStatus courseStatus;

    private LocalDateTime courseExpiredAt;

    private LocalDateTime courseCreatedAt;

    public static CourseDetailResult from(CourseJpaEntity courseJpaEntity) {
        return CourseDetailResult.builder()
                .courseId(courseJpaEntity.getId())
                .courseSeriesId(courseJpaEntity.getCourseSeriesJpaEntity().getId())
                .courseImageUrl(courseJpaEntity.getCourseImageUrl())
                .courseTitle(courseJpaEntity.getCourseTitle())
                .courseSubTitle(courseJpaEntity.getCourseSubTitle())
                .coursePrice(courseJpaEntity.getCoursePrice())
                .courseDescription(courseJpaEntity.getCourseDescription())
                .courseGenre(courseJpaEntity.getCourseGenre())
                .subjectCategory(courseJpaEntity.getSubjectCategory())
                .courseStatus(courseJpaEntity.getCourseStatus())
                .courseExpiredAt(courseJpaEntity.getCourseExpiredAt())
                .courseCreatedAt(courseJpaEntity.getCourseCreatedAt())
                .build();
    }
}
