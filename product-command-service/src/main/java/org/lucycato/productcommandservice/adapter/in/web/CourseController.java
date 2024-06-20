package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
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
            @RequestPart(name = "request")
            RegisterCourseRequest request,
            @RequestPart(name = "teacherImageFile", required = false)
            MultipartFile teacherImageFile,
            @RequestPart(name = "teacherCurriculumImageFile", required = false)
            MultipartFile teacherCurriculumImageFile,
            @RequestPart(name = "teacherCurriculumVideoFile", required = false)
            MultipartFile teacherCurriculumVideoFile
    ) {
        RegisterCourseCommand command = new RegisterCourseCommand(
                request.getTeacherId(),
                request.getCourseSeriesId(),
                request.getCourseTitle(),
                request.getCourseSubTitle(),
                request.getCoursePrice(),
                request.getCourseDescription(),
                request.getCourseGenre(),
                request.getSubjectCategory(),
                request.getExpiredAt(),
                teacherImageFile,
                teacherCurriculumImageFile,
                teacherCurriculumVideoFile
        );

        return courseUseCase.registerCourse(command);
    }

    @PatchMapping(value = "api/admin/v1/courses/{courseId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CourseDetail modifyCourse(
            @PathVariable
            Long courseId,
            @RequestPart(name = "request")
            RegisterCourseRequest request,
            @RequestPart(name = "teacherImageFile", required = false)
            MultipartFile teacherImageFile,
            @RequestPart(name = "teacherCurriculumImageFile", required = false)
            MultipartFile teacherCurriculumImageFile,
            @RequestPart(name = "teacherCurriculumVideoFile", required = false)
            MultipartFile teacherCurriculumVideoFile
    ) {
        ModifyCourseCommand command = new ModifyCourseCommand(
                courseId,
                request.getTeacherId(),
                request.getCourseSeriesId(),
                request.getCourseTitle(),
                request.getCourseSubTitle(),
                request.getCoursePrice(),
                request.getCourseDescription(),
                request.getCourseGenre(),
                request.getSubjectCategory(),
                request.getExpiredAt(),
                teacherImageFile,
                teacherCurriculumImageFile,
                teacherCurriculumVideoFile
        );

        return courseUseCase.modifyCourse(command);
    }

    @DeleteMapping("api/app/v1/courses/{courseId}")
    public Object deleteCourse(
            @PathVariable
            Long courseId
    ) {
        DeleteCourseCommand command = new DeleteCourseCommand(courseId);
        courseUseCase.deleteCourse(command);

        return new Object();
    }
}
