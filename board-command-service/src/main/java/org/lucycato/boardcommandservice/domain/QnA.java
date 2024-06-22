package org.lucycato.boardcommandservice.domain;

import lombok.*;
import org.lucycato.boardcommandservice.application.port.out.result.QnAResult;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QnA {

    private final AppUser userTeacher;

    private final AppUser userStudent;

    private final String title;

    private final String content;

    private final Lecture lecture;

    private String answer;

    public static QnA from(QnAResult result) {
        AppUser appUserTeacher = AppUser.builder()
                .userId(1L)
                .userName("domainTestUserTeacher")
                .build();
        AppUser appUserStudent = AppUser.builder()
                .userId(1L)
                .userName("domainTestUserStudent")
                .build();
        Lecture lecture = Lecture.builder()
                .lectureId(2L)
                .lectureName("domainTestLecture")
                .build();
        return QnA.builder()
                .userTeacher(appUserTeacher)
                .userStudent(appUserStudent)
                .lecture(lecture)
                .title(result.getTitle())
                .content(result.getContent())
                .answer(result.getAnswer())
                .build();
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AppUser {

        private final Long userId;

        private final String userName;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Lecture {

        private final Long lectureId;

        private final String lectureName;
    }
}
