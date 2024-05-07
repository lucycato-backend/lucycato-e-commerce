package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.domain.enums.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lecture {
    private Long lectureId;

    private Long teacherId;

    private Long lectureSeriesId;

    private String imageUrl;

    private Subject subject;

    private String title;

    private String description;

    private List<LectureContent> contents;

    private LectureTargetCategory targetCategory;

    private List<String> tags;

    private LectureGenre genre;

    private String composition;

    private Integer coursePeriod;

    private LectureProductWay productionWay;

    private LectureStatus status;

    private LocalDateTime createdAt;
}
