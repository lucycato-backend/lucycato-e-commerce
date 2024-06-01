package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.domain.enums.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseLecture {
    private Long lectureId;

    private Course course;

    private String lectureTitle;

    private String lectureDescription;

    private LectureCategory lectureCategory;

    private String lectureVideoUrl;

    private LectureStatus lectureStatus;

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Course {
        private final Long courseId;

        private final String courseTitle;

        private final CourseGenre courseGenre;

        private final SubjectCategory subjectCategory;

        private final CourseStatus courseStatus;
    }
}
