package org.lucycato.boardcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.boardcommandservice.adapter.out.persistence.entity.CourseReviewJpaEntity;
import org.lucycato.boardcommandservice.domain.CourseReview;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseReviewResult {

    private Long id;
    private Long studentId;

    private Long teacherId;

    private Long lectureId;

    private String title;

    private String content;

    private int score;

    public static CourseReviewResult from(CourseReviewJpaEntity entity) {
        return CourseReviewResult.builder()
                .id(entity.getId())
                .studentId(entity.getStudentId())
                .teacherId(entity.getTeacherId())
                .lectureId(entity.getLectureId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .score(entity.getScore())
                .build();
    }
}
