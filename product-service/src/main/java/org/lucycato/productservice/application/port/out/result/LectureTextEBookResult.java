package org.lucycato.productservice.application.port.out.result;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productservice.adapter.out.persistence.entity.LectureTextEBookR2dbcEntity;

@Getter
@NoArgsConstructor
public class LectureTextEBookResult {
    private Long lectureTextEBookId;

    public static LectureTextEBookResult from(LectureTextEBookR2dbcEntity entity) {
        return new LectureTextEBookResult();
    }
}
