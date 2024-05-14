package org.lucycato.productservice.adapter.out.persistence.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureSeriesImageVo {
    private Integer idx;

    private String imageUrl;
}
