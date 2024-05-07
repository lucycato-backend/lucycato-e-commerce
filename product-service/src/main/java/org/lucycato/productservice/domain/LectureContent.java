package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.domain.enums.LectureContentCategory;
import org.lucycato.productservice.domain.enums.LectureContentStatus;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureContent {
    private Long lectureContentId;

    private Long lectureId;

    private String title;

    private LectureContentCategory category;

    private String thumbnailImageUrl;

    private String videoUrl;

    private LectureContentStatus status;

    private LocalDateTime createdAt;
}
