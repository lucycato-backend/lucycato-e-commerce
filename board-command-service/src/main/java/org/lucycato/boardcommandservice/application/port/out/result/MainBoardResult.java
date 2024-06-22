package org.lucycato.boardcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.boardcommandservice.adapter.out.persistence.entity.MainNoticeJapEntity;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainBoardResult {

    private Long id;

    private Long userId;

    private String title;

    private String content;

    private String type;

    public static MainBoardResult from(MainNoticeJapEntity entity) {
        return MainBoardResult.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .title(entity.getTitle())
                .type(entity.getType())
                .content(entity.getContent())
                .type(entity.getType())
                .build();
    }

}
