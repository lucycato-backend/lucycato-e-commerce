package org.lucycato.boardcommandservice.adapter.in.web.requset;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseReviewRequest {

    private Long teacherId;

    private Long studentId;

    private String title;

    private String content;

    private int score;

    private Long lectureId;
}
