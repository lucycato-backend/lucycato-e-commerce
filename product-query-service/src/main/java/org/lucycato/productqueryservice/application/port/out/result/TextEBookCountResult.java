package org.lucycato.productqueryservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TextEBookCountResult {
    private Integer operatorTextEBookCount;

    private Integer nonOperatorTextEBookCount;
}
