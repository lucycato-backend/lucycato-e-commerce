package org.lucycato.productcommandservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseJpaEntity;
import org.lucycato.productcommandservice.domain.enums.LectureCategory;
import org.lucycato.productcommandservice.domain.enums.LectureStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureDetail {
    private Long lectureId;

    private Long courseId;

    private String lectureTitle;

    private String lectureDescription;

    private String lectureThumbnailImageUrl;

    private String lectureVideoUrl;

    private LectureCategory lectureCategory;

    private LectureStatus lectureStatus;

    private LocalDateTime lectureCreatedAt;
}
