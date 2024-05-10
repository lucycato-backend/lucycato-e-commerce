package org.lucycato.productservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisteredLectureReviewRequest {
    private Long lectureId;

    private String reviewTitle;

    private String reviewContent;
}
