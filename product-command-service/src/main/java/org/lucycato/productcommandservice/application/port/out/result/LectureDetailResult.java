package org.lucycato.productcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.LectureJpaEntity;
import org.lucycato.productcommandservice.domain.enums.LectureCategory;
import org.lucycato.productcommandservice.domain.enums.LectureStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureDetailResult {
    private Long lectureId;

    private Long courseId;

    private String lectureThumbnailImageUrl;

    private String lectureVideoUrl;

    private String lectureTitle;

    private String lectureDescription;

    private LectureCategory lectureCategory;

    private LectureStatus lectureStatus;

    private LocalDateTime lectureCreatedAt;

    public static LectureDetailResult from(LectureJpaEntity lectureJpaEntity) {
        return LectureDetailResult.builder()
                .lectureId(lectureJpaEntity.getId())
                .courseId(lectureJpaEntity.getCourseJpaEntity().getId())
                .lectureThumbnailImageUrl(lectureJpaEntity.getLectureThumbnailImageUrl())
                .lectureVideoUrl(lectureJpaEntity.getLectureVideoUrl())
                .lectureTitle(lectureJpaEntity.getLectureTitle())
                .lectureDescription(lectureJpaEntity.getLectureDescription())
                .lectureCategory(lectureJpaEntity.getLectureCategory())
                .lectureStatus(lectureJpaEntity.getLectureStatus())
                .lectureCreatedAt(lectureJpaEntity.getLectureCreatedAt())
                .build();
    }
}
