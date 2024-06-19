package org.lucycato.productqueryservice.domain;

import lombok.*;
import org.lucycato.productqueryservice.application.port.out.result.CourseResult;
import org.lucycato.productqueryservice.application.port.out.result.LectureResult;
import org.lucycato.productqueryservice.domain.enums.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseLecture {
    private final Long lectureId;

    private final Course course;

    private final String lectureTitle;

    private final String lectureDescription;

    private final LectureCategory lectureCategory;

    private final String lectureVideoUrl;

    private final LectureStatus lectureStatus;

    public static CourseLecture from(CourseResult courseResult, LectureResult lectureResult) {
        Course course = Course.builder()
                .courseId(courseResult.getCourseId())
                .courseTitle(courseResult.getCourseTitle())
                .courseGenre(courseResult.getCourseGenre())
                .subjectCategory(courseResult.getSubjectCategory())
                .courseStatus(courseResult.getCourseStatus())
                .build();
        return CourseLecture.builder()
                .lectureId(lectureResult.getLectureId())
                .course(course)
                .lectureTitle(lectureResult.getLectureTitle())
                .lectureDescription(lectureResult.getLectureDescription())
                .lectureCategory(lectureResult.getLectureCategory())
                .lectureVideoUrl(lectureResult.getLectureVideoUrl())
                .lectureStatus(lectureResult.getLectureStatus())
                .build();
    }

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
