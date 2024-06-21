package org.lucycato.productcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseSeriesJpaEntity;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSeriesDetailResult {
    private Long courseSeriesId;

    private Long teacherId;

    private String courseSeriesImageUrl;

    private String courseSeriesTitle;

    private String courseSeriesDescription;

    private SubjectCategory subjectCategory;

    private CourseSeriesCategory courseSeriesCategory;

    private CourseSeriesStatus courseSeriesStatus;

    private LocalDateTime courseSeriesCreatedAt;

    public static CourseSeriesDetailResult from(CourseSeriesJpaEntity courseSeriesJpaEntity) {
        return CourseSeriesDetailResult.builder()
                .courseSeriesId(courseSeriesJpaEntity.getId())
                .teacherId(courseSeriesJpaEntity.getTeacherJpaEntity().getId())
                .courseSeriesImageUrl(courseSeriesJpaEntity.getCourseSeriesImageUrl())
                .courseSeriesTitle(courseSeriesJpaEntity.getCourseSeriesTitle())
                .courseSeriesDescription(courseSeriesJpaEntity.getCourseSeriesDescription())
                .subjectCategory(courseSeriesJpaEntity.getSubjectCategory())
                .courseSeriesCategory(courseSeriesJpaEntity.getCourseSeriesCategory())
                .courseSeriesStatus(courseSeriesJpaEntity.getCourseSeriesStatus())
                .courseSeriesCreatedAt(courseSeriesJpaEntity.getCourseSeriesCreatedAt())
                .build();
    }
}
