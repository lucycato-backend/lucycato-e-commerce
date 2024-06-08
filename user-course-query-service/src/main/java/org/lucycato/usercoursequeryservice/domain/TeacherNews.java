package org.lucycato.usercoursequeryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.usercoursequeryservice.domain.enums.TeacherNewStatus;
import org.lucycato.usercoursequeryservice.domain.enums.TeacherNewsCategory;
import org.lucycato.usercoursequeryservice.domain.enums.TeacherStatus;
import org.lucycato.usercoursequeryservice.domain.enums.TeachingGenre;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherNews {
    private final Long newsId;

    private final Teacher teacher;

    private final String newsTitle;

    private final Integer newsViewCount;

    private final TeacherNewsCategory teacherNewsCategory;

    private final TeacherNewStatus teacherNewStatus;

    private final LocalDateTime createdAt;

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Teacher {
        private final Long teacherId;

        private final String teacherName;

        private final TeachingGenre teachingGenre;

        private final TeacherStatus teacherStatus;
    }
}
