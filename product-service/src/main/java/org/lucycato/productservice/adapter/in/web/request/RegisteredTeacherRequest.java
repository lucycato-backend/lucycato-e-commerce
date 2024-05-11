package org.lucycato.productservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productservice.domain.enums.TeachingGenre;

@Getter
@NoArgsConstructor
public class RegisteredTeacherRequest {
    private String teacherName;

    private String slogan;

    private String profileDescription;

    private TeachingGenre teachingGenre;


}
