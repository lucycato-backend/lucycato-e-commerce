package org.lucycato.usercoursequeryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.usercoursequeryservice.domain.enums.TeacherStatus;
import org.lucycato.usercoursequeryservice.domain.enums.TeachingGenre;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Teacher {
    private final Long teacherId;

    private final Integer teacherRank;

    private final String teacherName;

    private final String teacherSlogan;

    private final String teacherImage;

    private final Boolean isRecentCourseOpen;

    private final Boolean isRecentTeacherNews;

    private final TeachingGenre teachingGenre;

    private final TeacherStatus teacherStatus;

    private final LocalDateTime createdAt;

    public static Teacher create() {
        return null;
    }
}
