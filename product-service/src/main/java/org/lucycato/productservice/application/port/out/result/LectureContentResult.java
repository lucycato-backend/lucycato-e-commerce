package org.lucycato.productservice.application.port.out.result;

import lombok.*;
import org.lucycato.productservice.adapter.out.persistence.entity.LectureContentR2dbcEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureContentResult {
    private Long lectureContentId;

    public static LectureContentResult from(LectureContentR2dbcEntity entity) {
        return null;
    }
}
