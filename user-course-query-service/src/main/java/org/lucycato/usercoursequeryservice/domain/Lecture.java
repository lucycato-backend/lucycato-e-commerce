package org.lucycato.productqueryservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productqueryservice.domain.enums.LectureCategory;
import org.lucycato.productqueryservice.domain.enums.LectureStatus;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lecture {
    private Long lectureId;

    private Long courseId;

    private String lectureTitle;

    private String lectureDescription;

    private LectureCategory lectureCategory;

    private String lectureThumbnailImageUrl;

    private String lectureVideoUrl;

    private LectureStatus lectureStatus;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
