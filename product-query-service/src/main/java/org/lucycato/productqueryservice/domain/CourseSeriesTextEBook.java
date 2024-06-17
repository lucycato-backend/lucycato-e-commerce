package org.lucycato.productqueryservice.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesResult;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookResult;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import org.lucycato.productqueryservice.domain.enums.TextEBookStatus;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseSeriesTextEBook {
    private final Long textEBookId;

    private final CourseSeries courseSeries;

    private final String imageUrl;

    private final String title;

    private final SubjectCategory subjectCategory;

    private final TeachingGenre teachingGenre;

    private final TextEBookStatus textEBookStatus;

    public static CourseSeriesTextEBook from(CourseSeriesResult courseSeriesResult, TextEBookResult textEBookResult) {
        CourseSeries courseSeries = CourseSeries.builder()
                .courseSeriesId(courseSeriesResult.getCourseSeriesId())
                .teacherId(courseSeriesResult.getTeacherId())
                .courseSeriesTitle(courseSeriesResult.getCourseSeriesTitle())
                .build();

        return CourseSeriesTextEBook.builder()
                .textEBookId(textEBookResult.getTextEBookId())
                .courseSeries(courseSeries)
                .imageUrl(textEBookResult.getTextEBookImageUrl())
                .title(textEBookResult.getTextEBookTitle())
                .subjectCategory(textEBookResult.getSubjectCategory())
                .teachingGenre(textEBookResult.getTeachingGenre())
                .textEBookStatus(textEBookResult.getTextEBookStatus())
                .build();
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CourseSeries {
        private final Long courseSeriesId;

        private final Long teacherId;

        private final String courseSeriesTitle;
    }
}
