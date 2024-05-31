package org.lucycato.productservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lucycato.productservice.adapter.out.persistence.entity.TeacherNewsR2dbcEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherNewsResult {

    private Long teacherNewsId;

    public static TeacherNewsResult from(TeacherNewsR2dbcEntity entity) {
        return null;
    }
}
