package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.productcommandservice.adapter.in.web.request.RegisterCourseRequest;
import org.lucycato.productcommandservice.application.port.in.CourseUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteCourseCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyCourseCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterCourseCommand;
import org.lucycato.productcommandservice.domain.CourseDetail;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseUseCase courseUseCase;

    @PostMapping(value = "api/admin/v1/courses", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CourseDetail registerCourse(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestPart(name = "request")
            RegisterCourseRequest request,
            @RequestPart(name = "courseImageFile", required = false)
            MultipartFile courseImageFile
    ) {
        RegisterCourseCommand command = new RegisterCourseCommand(
                adminUserHeaderDetail.getAdminUserId(),
                request.getCourseSeriesId(),
                request.getCourseTitle(),
                request.getCourseSubTitle(),
                request.getCoursePrice(),
                request.getCourseDescription(),
                request.getCourseGenre(),
                request.getSubjectCategory(),
                request.getExpiredAt(),
                courseImageFile
        );

        return courseUseCase.registerCourse(command);
    }

    @PatchMapping(value = "api/admin/v1/courses/{courseId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CourseDetail modifyCourse(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long courseId,
            @RequestPart(name = "request")
            RegisterCourseRequest request,
            @RequestPart(name = "courseImageFile", required = false)
            MultipartFile courseImageFile
    ) {
        ModifyCourseCommand command = new ModifyCourseCommand(
                adminUserHeaderDetail.getAdminUserId(),
                courseId,
                request.getCourseSeriesId(),
                request.getCourseTitle(),
                request.getCourseSubTitle(),
                request.getCoursePrice(),
                request.getCourseDescription(),
                request.getCourseGenre(),
                request.getSubjectCategory(),
                request.getExpiredAt(),
                courseImageFile
        );

        return courseUseCase.modifyCourse(command);
    }

    @DeleteMapping("api/admin/v1/courses/{courseId}")
    public Object deleteCourse(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long courseId
    ) {
        DeleteCourseCommand command = new DeleteCourseCommand(
                adminUserHeaderDetail.getAdminUserId(),
                courseId
        );

        courseUseCase.deleteCourse(command);
        return new Object();
    }
}
