package org.lucycato.productqueryservice.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import org.lucycato.productqueryservice.domain.enums.TextEBookStatus;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseSeriesTextEBook {
    private final Long courseSeriesId;

    private final String title;

    private final TextEBook textEBook;

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TextEBook {
        private final Long textEBookId;

        private final String imageUrl;

        private final String title;

        private final SubjectCategory subjectCategory;

        private final TeachingGenre teachingGenre;

        private final TextEBookStatus textEBookStatus;
    }
}
