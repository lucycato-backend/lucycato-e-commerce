package org.lucycato.productcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.TeacherJpaEntity;
import org.lucycato.productcommandservice.domain.enums.TeacherStatus;
import org.lucycato.productcommandservice.domain.enums.TeachingGenre;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDetailResult {
    private Long teacherId;

    private Integer teacherRank;

    private String teacherName;

    private String teacherSlogan;

    private String teacherProfileDescription;

    private String teacherImage;

    private String curriculumImageUrl;

    private String curriculumVideoUrl;

    private TeachingGenre teachingGenre;

    private TeacherStatus teacherStatus;

    private LocalDateTime teacherCreatedAt;

    public static TeacherDetailResult from(TeacherJpaEntity teacherJpaEntity) {
        return TeacherDetailResult.builder()
                .teacherId(teacherJpaEntity.getId())
                .teacherRank(teacherJpaEntity.getTeacherRank())
                .teacherName(teacherJpaEntity.getTeacherName())
                .teacherSlogan(teacherJpaEntity.getTeacherSlogan())
                .teacherProfileDescription(teacherJpaEntity.getTeacherProfileDescription())
                .teacherImage(teacherJpaEntity.getTeacherImageUrl())
                .curriculumImageUrl(teacherJpaEntity.getTeacherCurriculumImageUrl())
                .curriculumVideoUrl(teacherJpaEntity.getTeacherCurriculumVideoUrl())
                .teachingGenre(teacherJpaEntity.getTeachingGenre())
                .teacherStatus(teacherJpaEntity.getTeacherStatus())
                .teacherCreatedAt(teacherJpaEntity.getTeacherCreatedAt())
                .build();
    }
}
