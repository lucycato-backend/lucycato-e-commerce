package org.lucycato.boardqueryservice.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherNotice {

    private final Long id;

    private final Teacher teacher;

    private String title;

    private String content;

    private String type;

    public static TeacherNotice from() {
        return null;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Teacher {

        private final Long teacherId;

        private final String teacherName;
    }
}
