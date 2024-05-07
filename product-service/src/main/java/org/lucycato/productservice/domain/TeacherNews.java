package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.domain.enums.NewStatus;
import org.lucycato.productservice.domain.enums.NewsCategory;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherNews {
    private Long newsId;

    private Long teacherId;

    private Integer viewCount;

    private NewsCategory category;

    private NewStatus status;

    private String title;

    private String content;

    private LocalDateTime createdAt;
}
