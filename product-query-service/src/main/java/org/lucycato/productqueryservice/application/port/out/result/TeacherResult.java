package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productqueryservice.domain.TeacherDetail;
import org.lucycato.productqueryservice.domain.enums.TeacherStatus;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResult {
    private Long teacherId;

    private Integer teacherRank;

    private String teacherName;

    private String teacherSlogan;

    private String teacherImage;

    private TeachingGenre teachingGenre;

    private TeacherStatus teacherStatus;

    private LocalDateTime createdAt;
}
