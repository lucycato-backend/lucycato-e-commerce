package org.lucycato.boardcommandservice.adapter.in.web.requset;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExamStoryRequest {

    private Long teacherId;

    private String title;

    private String content;

    private String type;
}
