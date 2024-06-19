package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesResult;
import org.lucycato.productqueryservice.application.port.out.result.TeacherResult;
import org.lucycato.productqueryservice.domain.enums.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherCourseSeries {
    private final Long courseSeriesId;

    private final Teacher teacher;

    private final String courseSeriesImageUrl;

    private final String courseSeriesTitle;

    private final String courseSeriesDescription;

    public static TeacherCourseSeries from(TeacherResult teacherResult, CourseSeriesResult courseSeriesResult) {
        Teacher teacher = Teacher.builder()
                .teacherId(teacherResult.getTeacherId())
                .teacherName(teacherResult.getTeacherName())
                .teachingGenre(teacherResult.getTeachingGenre())
                .teacherStatus(teacherResult.getTeacherStatus())
                .build();

        return TeacherCourseSeries.builder()
                .courseSeriesId(courseSeriesResult.getCourseSeriesId())
                .teacher(teacher)
                .courseSeriesImageUrl(courseSeriesResult.getCourseSeriesImageUrl())
                .courseSeriesTitle(courseSeriesResult.getCourseSeriesTitle())
                .courseSeriesDescription(courseSeriesResult.getCourseSeriesDescription())
                .build();
    }

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
