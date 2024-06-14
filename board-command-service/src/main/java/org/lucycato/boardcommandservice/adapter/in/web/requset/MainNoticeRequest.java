package org.lucycato.boardcommandservice.adapter.in.web.requset;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MainNoticeRequest {

    private Long userId;

    private String title;

    private String content;

    private String type;
}
