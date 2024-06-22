package org.lucycato.productcommandservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.lucycato.productcommandservice.application.port.out.result.LectureDetailResult;
import org.lucycato.productcommandservice.domain.enums.LectureCategory;
import org.lucycato.productcommandservice.domain.enums.LectureStatus;

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

    public static LectureDetail from(
            LectureDetailResult lectureDetailResult
    ) {
        return LectureDetail.builder()
                .lectureId(lectureDetailResult.getLectureId())
                .courseId(lectureDetailResult.getCourseId())
                .lectureTitle(lectureDetailResult.getLectureTitle())
                .lectureDescription(lectureDetailResult.getLectureDescription())
                .lectureThumbnailImageUrl(lectureDetailResult.getLectureThumbnailImageUrl())
                .lectureVideoUrl(lectureDetailResult.getLectureVideoUrl())
                .lectureCategory(lectureDetailResult.getLectureCategory())
                .lectureStatus(lectureDetailResult.getLectureStatus())
                .lectureCreatedAt(lectureDetailResult.getLectureCreatedAt())
                .build();
    }
}
