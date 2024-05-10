package org.lucycato.productservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productservice.domain.enums.LectureGenre;
import org.lucycato.productservice.domain.enums.LectureTargetStudentCategory;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisteredLectureRequest {
    private Long lectureSeriesId;

    private String lectureTitle;

    private String lectureSubTitle;

    private Integer lecturePrice;

    private String lectureDescription;

    private String lectureComposition;

    private List<LectureTargetStudentCategory> lectureTargetStudentCategories;

    private LectureGenre lectureGenre;

    private LocalDateTime expiredAt;
}
