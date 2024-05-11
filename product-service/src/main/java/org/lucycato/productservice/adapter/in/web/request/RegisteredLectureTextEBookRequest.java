package org.lucycato.productservice.adapter.in.web.request;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productservice.domain.enums.SubjectCategory;

@Getter
@NoArgsConstructor
public class RegisteredLectureTextEBookRequest {
    private Long lectureId;

    private String eBookUniqueCode;

    private String title;

    private String description;

    private String tableOfContents;

    private String author;

    private String publisher;

    private Integer page;
}
