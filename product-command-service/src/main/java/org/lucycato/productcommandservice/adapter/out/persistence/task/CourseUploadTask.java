package org.lucycato.productcommandservice.adapter.out.persistence.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.domain.enums.ProductBroadcastingCategory;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseUploadTask {
    private ProductBroadcastingCategory productBroadcastingCategory;

    private Long courseId;

    private Long teacherId;
}
