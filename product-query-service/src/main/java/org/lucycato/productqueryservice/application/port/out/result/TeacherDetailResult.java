package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productqueryservice.domain.enums.TeacherStatus;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDetailResult {
    private Long teacherId;

    private Integer teacherRank;

    private String teacherName;

    private String teacherSlogan;

    private String teacherProfileDescription;

    private String teacherImageUrl;

    private String curriculumImageUrl;

    private String curriculumVideoUrl;

    private TeachingGenre teachingGenre;

    private TeacherStatus teacherStatus;

    private LocalDateTime teacherCreatedAt;
}
