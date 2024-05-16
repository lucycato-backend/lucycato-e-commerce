package org.lucycato.productservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productservice.adapter.out.persistence.entity.LectureR2dbcEntity;
import org.lucycato.productservice.domain.enums.SubjectCategory;
import org.lucycato.productservice.domain.enums.TeachingGenre;

@Getter
@NoArgsConstructor
public class LectureResult {
    private Long lectureId;
    private SubjectCategory subjectCategory;
    private TeachingGenre teachingGenre;

    public static LectureResult from(LectureR2dbcEntity entity) {
        return new LectureResult();
    }
}
