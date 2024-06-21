package org.lucycato.boardcommandservice.domain;

import lombok.*;
import org.lucycato.boardcommandservice.application.port.out.result.MainBoardResult;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MainBoard {

    private final AppUser user;

    private final String title;

    private String content;

    private final String type;

    private final LocalDateTime createdAt;

    public static MainBoard from(MainBoardResult boardResult) {
        AppUser appUser = AppUser.builder()
                .userId(1L)
                .userName("domainTestUser")
                .build();
        return MainBoard.builder()
                .user(appUser)
                .title(boardResult.getTitle())
                .content(boardResult.getContent())
                .type(boardResult.getType())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AppUser {

        private final Long userId;

        private final String userName;
    }

}
