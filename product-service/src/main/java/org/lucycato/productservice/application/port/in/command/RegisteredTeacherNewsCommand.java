package org.lucycato.productservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.productservice.domain.enums.LectureContentCategory;
import org.lucycato.productservice.domain.enums.TeacherNewStatus;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisteredTeacherNewsCommand extends SelfValidating<RegisteredTeacherNewsCommand> {
    @NotNull
    private Long teacherId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotNull
    private TeacherNewStatus teacherNewStatus;

    public RegisteredTeacherNewsCommand(Long teacherId, String title, String content, TeacherNewStatus teacherNewStatus) {
        this.teacherId = teacherId;
        this.title = title;
        this.content = content;
        this.teacherNewStatus = teacherNewStatus;

        this.validateSelf();
    }
}
