package org.lucycato.productservice.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productservice.domain.enums.TeacherNewStatus;

@Getter
@NoArgsConstructor
public class RegisteredTeacherNewsRequest {
    private Long teacherId;

    private String title;

    private String content;

    private TeacherNewStatus teacherNewStatus;
}
