package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.productcommandservice.adapter.in.web.request.RegisterTeacherRequest;
import org.lucycato.productcommandservice.application.port.in.TeacherUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteTeacherCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyTeacherCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterTeacherCommand;
import org.lucycato.productcommandservice.domain.TeacherDetail;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherUseCase teacherUseCase;

    @PostMapping(value = "api/admin/v1/teachers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TeacherDetail registerTeacher(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestPart(name = "request")
            RegisterTeacherRequest request,
            @RequestPart(name = "teacherImageFile")
            MultipartFile teacherImageFile,
            @RequestPart(name = "teacherCurriculumImageFile")
            MultipartFile teacherCurriculumImageFile,
            @RequestPart(name = "teacherCurriculumVideoFile")
            MultipartFile teacherCurriculumVideoFile
    ) {
        RegisterTeacherCommand command = new RegisterTeacherCommand(
                adminUserHeaderDetail.getAdminUserId(),
                request.getTeacherRank(),
                request.getTeacherName(),
                request.getTeacherSlogan(),
                request.getTeacherProfileDescription(),
                request.getTeachingGenre(),
                teacherImageFile,
                teacherCurriculumImageFile,
                teacherCurriculumVideoFile
        );

        return teacherUseCase.registerTeacher(command);
    }

    @PatchMapping(value = "api/admin/v1/teachers/{teacherId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TeacherDetail modifyTeacher(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long teacherId,
            @RequestPart(name = "request")
            RegisterTeacherRequest request,
            @RequestPart(name = "teacherImageFile")
            MultipartFile teacherImageFile,
            @RequestPart(name = "teacherCurriculumImageFile")
            MultipartFile teacherCurriculumImageFile,
            @RequestPart(name = "teacherCurriculumVideoFile")
            MultipartFile teacherCurriculumVideoFile
    ) {
        ModifyTeacherCommand command = new ModifyTeacherCommand(
                adminUserHeaderDetail.getAdminUserId(),
                teacherId,
                request.getTeacherRank(),
                request.getTeacherName(),
                request.getTeacherSlogan(),
                request.getTeacherProfileDescription(),
                request.getTeachingGenre(),
                teacherImageFile,
                teacherCurriculumImageFile,
                teacherCurriculumVideoFile
        );

        return teacherUseCase.modifyTeacher(command);
    }

    @DeleteMapping("api/admin/v1/teachers/{teacherId}")
    public Object deleteTeacher(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long teacherId
    ) {
        DeleteTeacherCommand command = new DeleteTeacherCommand(
                adminUserHeaderDetail.getAdminUserId(),
                teacherId
        );
        teacherUseCase.deleteTeacher(command);

        return new Object();
    }
}
