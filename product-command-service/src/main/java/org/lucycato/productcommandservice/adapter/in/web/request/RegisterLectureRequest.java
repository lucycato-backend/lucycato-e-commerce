package org.lucycato.productcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.domain.enums.LectureCategory;

@Getter
@NoArgsConstructor
public class RegisterLectureRequest {
    private Long courseId;

    private String lectureTitle;

    private String lectureDescription;

    private LectureCategory lectureCategory;
}
