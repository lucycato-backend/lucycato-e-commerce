package org.lucycato.productcommandservice.adapter.in.web;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productcommandservice.adapter.in.web.request.RegisterCourseSeriesRequest;
import org.lucycato.productcommandservice.application.port.in.CourseSeriesUseCase;
import org.lucycato.productcommandservice.application.port.in.CourseUseCase;
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
            @RequestPart(name = "request")
            RegisterCourseSeriesRequest request,
            @RequestPart(name = "courseSeriesImageFile")
            MultipartFile courseSeriesImageFile,
            @RequestPart(name = "courseSeriesExplainImageFiles")
            List<MultipartFile> courseSeriesExplainImageFiles
    ) {
        RegisterCourseSeriesCommand command = new RegisterCourseSeriesCommand(
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
            @PathVariable
            Long courseSeriesId,
            @RequestPart(name = "request")
            RegisterCourseSeriesRequest request,
            @RequestPart(name = "courseSeriesImageFile")
            MultipartFile courseSeriesImageFile,
            @RequestPart(name = "courseSeriesExplainImageFiles")
            List<MultipartFile> courseSeriesExplainImageFiles
    ) {
        ModifyCourseSeriesCommand command = new ModifyCourseSeriesCommand(
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
            @PathVariable
            Long courseSeriesId
    ) {
        DeleteCourseSeriesCommand command = new DeleteCourseSeriesCommand(courseSeriesId);
        courseSeriesUseCase.deleteCourseSeries(command);

        return new Object();
    }
}
