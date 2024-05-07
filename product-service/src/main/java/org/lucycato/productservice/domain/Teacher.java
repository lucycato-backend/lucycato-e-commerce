package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.domain.enums.ProductGenre;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Teacher {
    private final Long teacherId;

    private final Integer rank;

    private final ProductGenre genre;

    private final String name;

    private final String slogan;

    private final String teacherMainImage;

    private final String teacherDetailImage;

    private final String curriculumImageUrl;

    private final String curriculumVideoUrl;

    private final Integer operatorAllLectureCount;

    private final Integer operatorCompleteLectureCount;

    private final Integer operatorProgressLectureCount;
}
