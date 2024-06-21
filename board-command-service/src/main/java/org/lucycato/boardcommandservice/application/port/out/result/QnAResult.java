package org.lucycato.boardcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.boardcommandservice.adapter.out.persistence.entity.TeacherQnAJpaEntity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnAResult {

    private Long id;

    private Long studentId;

    private Long teacherId;

    private Long lectureId;

    private String title;

    private String content;

    private String answer;

    public static QnAResult from(TeacherQnAJpaEntity entity) {
        return QnAResult.builder()
                .id(entity.getId())
                .studentId(entity.getStudentId())
                .teacherId(entity.getTeacherId())
                .lectureId(entity.getLectureId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .answer(entity.getAnswer())
                .build();
    }
}
