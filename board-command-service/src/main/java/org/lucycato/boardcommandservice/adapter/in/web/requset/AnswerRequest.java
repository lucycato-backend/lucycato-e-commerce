package org.lucycato.boardcommandservice.adapter.in.web.requset;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerRequest {
    private Long QnAid;

    private String answer;
}
