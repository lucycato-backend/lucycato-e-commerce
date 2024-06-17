package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.application.port.out.result.CourseResult;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookResult;
import org.lucycato.productqueryservice.domain.enums.*;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseTextEBook {
    private final Long textEBookId;

    private final Course course;

    private final String textEBookUniqueCode;

    private final String textEBookImageUrl;

    private final String textEBookTitle;

    private final String textEBookDescription;

    private final String textEBookTableOfContents;

    private final String textEBookAuthor;

    private final String textEBookPublisher;

    private final String textEBookPreviewDownloadUrl;

    private final Integer textEBookPage;

    private final SubjectCategory subjectCategory;

    private final TeachingGenre teachingGenre;

    private final TextEBookStatus textEBookStatus;

    private final LocalDateTime textEBookPublishedAt;

    public static CourseTextEBook from(CourseResult courseResult, TextEBookResult textEBookResult) {
        Course course = Course.builder()
                .courseId(courseResult.getCourseId())
                .courseTitle(courseResult.getCourseTitle())
                .courseGenre(courseResult.getCourseGenre())
                .subjectCategory(courseResult.getSubjectCategory())
                .courseStatus(courseResult.getCourseStatus())
                .build();
        return CourseTextEBook.builder()
                .textEBookId(textEBookResult.getTextEBookId())
                .course(course)
                .textEBookUniqueCode(textEBookResult.getTextEBookUniqueCode())
                .textEBookImageUrl(textEBookResult.getTextEBookImageUrl())
                .textEBookTitle(textEBookResult.getTextEBookTitle())
                .textEBookDescription(textEBookResult.getTextEBookDescription())
                .textEBookTableOfContents(textEBookResult.getTextEBookTableOfContents())
                .textEBookAuthor(textEBookResult.getTextEBookAuthor())
                .textEBookPublisher(textEBookResult.getTextEBookPublisher())
                .textEBookPreviewDownloadUrl(textEBookResult.getTextEBookPreviewDownloadUrl())
                .textEBookPage(textEBookResult.getTextEBookPage())
                .subjectCategory(textEBookResult.getSubjectCategory())
                .teachingGenre(textEBookResult.getTeachingGenre())
                .textEBookStatus(textEBookResult.getTextEBookStatus())
                .textEBookPublishedAt(textEBookResult.getTextEBookPublishedAt())
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
