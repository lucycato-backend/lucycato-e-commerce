package org.lucycato.productservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productservice.domain.enums.LectureContentCategory;

@Getter
@NoArgsConstructor
public class RegisteredLectureContentRequest {
    private Long lectureId;

    private String lectureContentTitle;

    private LectureContentCategory lectureContentCategory;
}
