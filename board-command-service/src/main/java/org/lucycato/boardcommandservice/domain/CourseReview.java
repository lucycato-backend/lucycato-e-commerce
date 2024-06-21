package org.lucycato.boardcommandservice.domain;

import lombok.*;
import org.lucycato.boardcommandservice.application.port.out.result.CourseReviewResult;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseReview {

    private final AppUser userTeacher;

    private final AppUser userStudent;

    private final String title;

    private final String content;

    private final int score;

    private final Lecture lecture;

    public static CourseReview from(CourseReviewResult result) {
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
        return CourseReview.builder()
                .userTeacher(appUserTeacher)
                .userStudent(appUserStudent)
                .title(result.getTitle())
                .content(result.getContent())
                .score(result.getScore())
                .lecture(lecture)
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
