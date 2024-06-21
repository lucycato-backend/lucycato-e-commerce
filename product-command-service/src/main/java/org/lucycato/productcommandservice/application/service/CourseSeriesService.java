package org.lucycato.productcommandservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productcommandservice.application.port.in.CourseSeriesUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteCourseSeriesCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyCourseSeriesCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterCourseSeriesCommand;
import org.lucycato.productcommandservice.application.port.out.*;
import org.lucycato.productcommandservice.domain.CourseSeriesDetail;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productcommandservice.error.ProductCommandErrorCodeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseSeriesService implements CourseSeriesUseCase {
    private final CourseSeriesPort courseSeriesPort;

    private final FileStoragePort fileStoragePort;

    private final UserAuthPort userAuthPort;

    private final TeacherQueryPort teacherQueryPort;

    @Override
    public CourseSeriesDetail registerCourseSeries(RegisterCourseSeriesCommand command) {
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), command.getTeacherId())) {
            String courseSeriesImageUrl = "";
            if (command.getCourseSeriesImageFile() != null) {
                courseSeriesImageUrl = fileStoragePort.saveImageFile(command.getCourseSeriesImageFile());
            }
            List<String> courseSeriesExplainImageUrls = List.of();
            if (command.getCourseSeriesExplainImageFiles() != null && !command.getCourseSeriesExplainImageFiles().isEmpty()) {
                courseSeriesExplainImageUrls = fileStoragePort.saveImageFiles(command.getCourseSeriesExplainImageFiles());
            }

            return CourseSeriesDetail.from(courseSeriesPort.registerCourseSeries(
                    command.getTeacherId(),
                    courseSeriesImageUrl,
                    command.getCourseSeriesTitle(),
                    command.getCourseSeriesDescription(),
                    courseSeriesExplainImageUrls,
                    command.getSubjectCategory(),
                    command.getCourseSeriesCategory(),
                    CourseSeriesStatus.REGISTERED
            ));
        }

        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
    }

    @Override
    public CourseSeriesDetail modifyCourseSeries(ModifyCourseSeriesCommand command) {
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), command.getTeacherId())) {
            String courseSeriesImageUrl = "";
            if (command.getCourseSeriesImageFile() != null) {
                courseSeriesImageUrl = fileStoragePort.saveImageFile(command.getCourseSeriesImageFile());
            }
            List<String> courseSeriesExplainImageUrls = List.of();
            if (command.getCourseSeriesExplainImageFiles() != null && !command.getCourseSeriesExplainImageFiles().isEmpty()) {
                courseSeriesExplainImageUrls = fileStoragePort.saveImageFiles(command.getCourseSeriesExplainImageFiles());
            }

            return CourseSeriesDetail.from(courseSeriesPort.modifyCourseSeries(
                    command.getCourseSeriesId(),
                    command.getTeacherId(),
                    courseSeriesImageUrl,
                    command.getCourseSeriesTitle(),
                    command.getCourseSeriesDescription(),
                    courseSeriesExplainImageUrls,
                    command.getSubjectCategory(),
                    command.getCourseSeriesCategory()
            ));
        }

        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);

    }

    @Override
    public void deleteCourseSeries(DeleteCourseSeriesCommand command) {
        Long teacherId = teacherQueryPort.getTeacherIdByCourseSeriesId(command.getCourseSeriesId());
        if (userAuthPort.checkAuthToChangeTeacher(command.getCourseSeriesId(), teacherId)) {
            courseSeriesPort.deleteCourseSeries(command.getCourseSeriesId());
        } else {
            throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
        }
    }
}
