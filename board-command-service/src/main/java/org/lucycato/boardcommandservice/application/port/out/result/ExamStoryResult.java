package org.lucycato.boardcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.boardcommandservice.adapter.out.persistence.entity.ExamStoryJpaEntity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamStoryResult {
    private Long id;

    private Long studentId;

    private Long teacherId;

    private String title;

    private String content;

    private String type;

    public static ExamStoryResult from(ExamStoryJpaEntity entity) {
        return ExamStoryResult.builder()
                .id(entity.getId())
                .studentId(entity.getStudentId())
                .teacherId(entity.getTeacherId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .type(entity.getType())
                .build();
    }
}
