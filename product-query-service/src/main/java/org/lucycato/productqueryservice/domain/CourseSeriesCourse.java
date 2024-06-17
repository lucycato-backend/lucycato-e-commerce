package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.application.port.out.result.CourseResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesResult;
import org.lucycato.productqueryservice.application.port.out.result.TeacherResult;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookResult;
import org.lucycato.productqueryservice.domain.enums.*;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseSeriesCourse {
    private final Long courseId;

    private final Teacher teacher;

    private final TextEBook textEBook;

    private final CourseSeries courseSeries;

    private final String courseTitle;

    private final String courseSubTitle;

    private final Integer coursePrice;

    private final String courseImageUrl;

    private final CourseGenre courseGenre;

    private final SubjectCategory subjectCategory;

    private final CourseStatus courseStatus;

    private final LocalDateTime expiredAt;

    private final LocalDateTime createdAt;

    public static CourseSeriesCourse from(
            CourseResult courseResult,
            TeacherResult teacherResult,
            CourseSeriesResult courseSeriesResult,
            TextEBookResult textEBookResult
    ) {
        Teacher teacher = Teacher.builder()
                .teacherId(teacherResult.getTeacherId())
                .teacherName(teacherResult.getTeacherName())
                .build();

        CourseSeries courseSeries = CourseSeries.builder()
                .courseSeriesId(courseSeriesResult.getCourseSeriesId())
                .courseSeriesTitle(courseSeriesResult.getCourseSeriesTitle())
                .courseSeriesImageUrl(courseSeriesResult.getCourseSeriesImageUrl())
                .courseSeriesCategory(courseSeriesResult.getCourseSeriesCategory())
                .courseSeriesStatus(courseSeriesResult.getCourseSeriesStatus())
                .build();

        TextEBook textEBook = TextEBook.builder()
                .textEBookId(textEBookResult.getTextEBookId())
                .textEBookUniqueCode(textEBookResult.getTextEBookUniqueCode())
                .textEBookImageUrl(textEBookResult.getTextEBookImageUrl())
                .textEBookTitle(textEBookResult.getTextEBookTitle())
                .textEBookPreviewDownloadUrl(textEBookResult.getTextEBookPreviewDownloadUrl())
                .textEBookStatus(textEBookResult.getTextEBookStatus())
                .build();
        return CourseSeriesCourse.builder()
                .courseId(courseResult.getCourseId())
                .teacher(teacher)
                .textEBook(textEBook)
                .courseSeries(courseSeries)
                .courseTitle(courseResult.getCourseTitle())
                .courseSubTitle(courseResult.getCourseSubTitle())
                .coursePrice(courseResult.getCoursePrice())
                .courseImageUrl(courseResult.getCourseImageUrl())
                .courseGenre(courseResult.getCourseGenre())
                .subjectCategory(courseResult.getSubjectCategory())
                .courseStatus(courseResult.getCourseStatus())
                .expiredAt(courseResult.getExpiredAt())
                .createdAt(courseResult.getCreatedAt())
                .build();
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Teacher {
        private final Long teacherId;

        private final String teacherName;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TextEBook {
        private final Long textEBookId;

        private final String textEBookUniqueCode;

        private final String textEBookImageUrl;

        private final String textEBookTitle;

        private final String textEBookPreviewDownloadUrl;

        private final TextEBookStatus textEBookStatus;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CourseSeries {
        private final Long courseSeriesId;

        private final String courseSeriesTitle;

        private final String courseSeriesImageUrl;

        private final CourseSeriesCategory courseSeriesCategory;

        private final CourseSeriesStatus courseSeriesStatus;
    }
}
