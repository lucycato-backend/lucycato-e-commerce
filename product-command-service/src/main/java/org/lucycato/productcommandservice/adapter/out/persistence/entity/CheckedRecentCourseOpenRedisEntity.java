package org.lucycato.productcommandservice.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckedRecentCourseOpenRedisEntity {
    private Long courseId;

    private Long teacherId;
}
