package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.application.port.out.result.TeacherResult;
import org.lucycato.productqueryservice.domain.enums.TeacherStatus;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

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

    private final Boolean isRecentTeacherNotice;

    private final TeachingGenre teachingGenre;

    private final TeacherStatus teacherStatus;

    private final LocalDateTime teacherCreatedAt;

    public static Teacher from(
            TeacherResult teacherResult,
            Boolean isRecentCourseOpen,
            Boolean isRecentTeacherNews
    ) {
        return Teacher.builder()
                .teacherId(teacherResult.getTeacherId())
                .teacherRank(teacherResult.getTeacherRank())
                .teacherName(teacherResult.getTeacherName())
                .teacherSlogan(teacherResult.getTeacherSlogan())
                .teacherImage(teacherResult.getTeacherImageUrl())
                .isRecentCourseOpen(isRecentCourseOpen)
                .isRecentTeacherNotice(isRecentTeacherNews)
                .teachingGenre(teacherResult.getTeachingGenre())
                .teacherStatus(teacherResult.getTeacherStatus())
                .teacherCreatedAt(teacherResult.getTeacherCreatedAt())
                .build();
    }
}
