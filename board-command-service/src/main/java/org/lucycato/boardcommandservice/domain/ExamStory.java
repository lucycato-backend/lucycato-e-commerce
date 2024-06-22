package org.lucycato.boardcommandservice.domain;


import lombok.*;
import org.lucycato.boardcommandservice.application.port.out.result.ExamStoryResult;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExamStory {

    private final AppUser userTeacher;

    private final AppUser userStudent;

    private final String title;

    private String content;

    private String type;

    public static ExamStory from(ExamStoryResult result) {
        AppUser appUserTeacher = AppUser.builder()
                .userId(1L)
                .userName("domainTestUserTeacher")
                .build();
        AppUser appUserStudent = AppUser.builder()
                .userId(1L)
                .userName("domainTestUserStudent")
                .build();
        return ExamStory.builder()
                .userTeacher(appUserTeacher)
                .userStudent(appUserStudent)
                .title(result.getTitle())
                .content(result.getContent())
                .type(result.getType())
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
