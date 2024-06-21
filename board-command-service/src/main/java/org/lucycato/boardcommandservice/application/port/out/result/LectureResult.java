package org.lucycato.boardcommandservice.application.port.out.result;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureResult {

    private Long lectureId;

    private String lectureName;
}
