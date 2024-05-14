package org.lucycato.productservice.application.port.out.result;

import lombok.*;
import org.lucycato.productservice.adapter.out.persistence.entity.TeacherR2dbcEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResult {
    private Long teacherId;

    public static TeacherResult from(TeacherR2dbcEntity entity) {
        return null;
    }
}
