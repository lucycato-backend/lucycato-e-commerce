package org.lucycato.productservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lucycato.productservice.domain.enums.TeacherImageStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherImageVo {
    private String imageUrl;

    private TeacherImageStatus status;
}
