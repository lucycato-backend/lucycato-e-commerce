package org.lucycato.productcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.domain.enums.TeachingGenre;

@Getter
@NoArgsConstructor
public class RegisterTeacherRequest {
    private Integer teacherRank;

    private String teacherName;

    private String teacherSlogan;

    private String teacherProfileDescription;

    private TeachingGenre teachingGenre;
}
