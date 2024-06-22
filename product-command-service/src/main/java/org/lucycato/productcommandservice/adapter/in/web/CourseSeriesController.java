package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.productcommandservice.adapter.in.web.request.RegisterCourseSeriesRequest;
import org.lucycato.productcommandservice.application.port.in.CourseSeriesUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteCourseSeriesCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyCourseSeriesCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterCourseSeriesCommand;
import org.lucycato.productcommandservice.domain.CourseSeriesDetail;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CourseSeriesController {
    private final CourseSeriesUseCase courseSeriesUseCase;

    @PostMapping(value = "api/admin/v1/course-series", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CourseSeriesDetail registerCourseSeries(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestPart(name = "request")
            RegisterCourseSeriesRequest request,
            @RequestPart(name = "courseSeriesImageFile", required = false)
            MultipartFile courseSeriesImageFile,
            @RequestPart(name = "courseSeriesExplainImageFiles", required = false)
            List<MultipartFile> courseSeriesExplainImageFiles
    ) {
        RegisterCourseSeriesCommand command = new RegisterCourseSeriesCommand(
                adminUserHeaderDetail.getAdminUserId(),
                request.getTeacherId(),
                request.getCourseSeriesTitle(),
                request.getCourseSeriesDescription(),
                request.getSubjectCategory(),
                request.getCourseSeriesCategory(),
                courseSeriesImageFile,
                courseSeriesExplainImageFiles
        );

        return courseSeriesUseCase.registerCourseSeries(command);
    }

    @PatchMapping(value = "api/admin/v1/course-series/{courseSeriesId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CourseSeriesDetail modifyCourseSeries(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long courseSeriesId,
            @RequestPart(name = "request")
            RegisterCourseSeriesRequest request,
            @RequestPart(name = "courseSeriesImageFile", required = false)
            MultipartFile courseSeriesImageFile,
            @RequestPart(name = "courseSeriesExplainImageFiles", required = false)
            List<MultipartFile> courseSeriesExplainImageFiles
    ) {
        ModifyCourseSeriesCommand command = new ModifyCourseSeriesCommand(
                adminUserHeaderDetail.getAdminUserId(),
                courseSeriesId,
                request.getTeacherId(),
                request.getCourseSeriesTitle(),
                request.getCourseSeriesDescription(),
                request.getSubjectCategory(),
                request.getCourseSeriesCategory(),
                courseSeriesImageFile,
                courseSeriesExplainImageFiles
        );

        return courseSeriesUseCase.modifyCourseSeries(command);
    }

    @DeleteMapping("api/admin/v1/course-series/{courseSeriesId}")
    public Object deleteCourseSeries(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long courseSeriesId
    ) {
        DeleteCourseSeriesCommand command = new DeleteCourseSeriesCommand(
                adminUserHeaderDetail.getAdminUserId(),
                courseSeriesId
        );
        courseSeriesUseCase.deleteCourseSeries(command);

        return new Object();
    }
}
