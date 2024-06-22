package org.lucycato.boardcommandservice.domain;

import lombok.*;
import org.lucycato.boardcommandservice.application.port.out.result.TeacherNoticeResult;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherNotice {

    private final AppUser user;

    private final String title;

    private String content;

    private final String type;

    private final LocalDateTime createdAt;


    public static TeacherNotice from(TeacherNoticeResult result) {
        AppUser appUser = AppUser.builder()
                .userId(1L)
                .userName("domainTestUser")
                .build();
        return TeacherNotice.builder()
                .user(appUser)
                .title(result.getTitle())
                .content(result.getContent())
                .type(result.getType())
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
