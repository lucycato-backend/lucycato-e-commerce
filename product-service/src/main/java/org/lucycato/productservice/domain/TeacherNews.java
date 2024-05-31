package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.application.port.out.result.TeacherNewsResult;
import org.lucycato.productservice.domain.enums.TeacherNewStatus;
import org.lucycato.productservice.domain.enums.TeacherNewssCategory;
import org.lucycato.productservice.domain.enums.TeachingGenre;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherNews {
    private final Long teacherNewsId;

    private final String teacherName;

    private final String teacherNewsTitle;

    private final String teacherNewsContent;

    private final Integer teacherNewsViewCount;

    private final TeachingGenre teachingGenre;

    private final TeacherNewssCategory teacherNewssCategory;

    private final TeacherNewStatus teacherNewStatus;

    private final LocalDateTime createdAt;

    public static TeacherNews from(TeacherNewsResult teacherNewsResult) {
        return null;
    }

    public static Record createRecord() {
        return null;
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Record {
        private final Long teacherNewsId;

        private final String teacherName;

        private final String teacherNewsTitle;

        private final Integer teacherNewsViewCount;

        private final TeachingGenre teachingGenre;

        private final TeacherNewssCategory teacherNewssCategory;

        private final TeacherNewStatus teacherNewStatus;

        private final LocalDateTime createdAt;
    }
}
