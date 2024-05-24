package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.domain.enums.CourseGenre;
import org.lucycato.productqueryservice.domain.enums.CourseStatus;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherCourseSeries {
    private final Long courseSeriesId;

    private final String courseImageUrl;

    private final String courseTitle;

    private final String courseDescription;

    private final Teacher teacher;

    private final List<Course> courses;

    public static TeacherCourseSeries createIsNotSimple() {
        return null;
    }

    public static TeacherCourseSeries createSimple() {
        return null;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Course {
        private final Long courseId;

        private final CoursePreViewUrl coursePreViewUrl;

        private final CourseTextEBook courseTextEBook;

        private final String courseTitle;

        private final String courseSubTitle;

        private final Integer coursePrice;

        private final String courseImageUrl;

        private final CourseGenre courseGenre;

        private final SubjectCategory subjectCategory;

        private final CourseStatus courseStatus;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Teacher {
        private final Long teacherId;

        private final String teacherName;

        private final TeachingGenre teachingGenre;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CourseTextEBookPreView {
        private final Long textEBookId;

        private final String textEBookUniqueCode;

        private final String textEBookTitle;

        private final String textEBookPreviewDownLoadUrl;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CoursePreViewUrl {
        private final String otVideoUrl;

        private final String previewVideoUrl;
    }

}
