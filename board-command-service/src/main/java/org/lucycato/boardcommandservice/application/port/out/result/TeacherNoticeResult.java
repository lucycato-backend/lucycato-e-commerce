package org.lucycato.boardcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.boardcommandservice.adapter.out.persistence.entity.TeacherNoticeJpaEntity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherNoticeResult {

    private Long id;

    private Long teacherId;

    private String title;

    private String content;

    private String type;

    public static TeacherNoticeResult from(TeacherNoticeJpaEntity entity) {
        return TeacherNoticeResult.builder()
                .id(entity.getId())
                .teacherId(entity.getTeacherId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .type(entity.getType())
                .build();
    }
}
