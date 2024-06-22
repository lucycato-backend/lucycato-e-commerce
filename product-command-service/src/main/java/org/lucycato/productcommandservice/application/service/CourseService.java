package org.lucycato.productcommandservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productcommandservice.application.port.in.CourseUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteCourseCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyCourseCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterCourseCommand;
import org.lucycato.productcommandservice.application.port.out.CoursePort;
import org.lucycato.productcommandservice.application.port.out.FileStoragePort;
import org.lucycato.productcommandservice.application.port.out.TeacherQueryPort;
import org.lucycato.productcommandservice.application.port.out.UserAuthPort;
import org.lucycato.productcommandservice.application.port.out.result.CourseDetailResult;
import org.lucycato.productcommandservice.domain.CourseDetail;
import org.lucycato.productcommandservice.domain.enums.CourseStatus;
import org.lucycato.productcommandservice.domain.enums.ProductBroadcastingCategory;
import org.lucycato.productcommandservice.error.ProductCommandErrorCodeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService implements CourseUseCase {
    private final CoursePort coursePort;

    private final FileStoragePort fileStoragePort;

    private final UserAuthPort userAuthPort;

    private final TeacherQueryPort teacherQueryPort;

    @Override
    public CourseDetail registerCourse(RegisterCourseCommand command) {
        Long teacherId = teacherQueryPort.getTeacherIdByCourseSeriesId(command.getCourseSeriesId());
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), teacherId)) {
            String courseImageUrl = "";
            if (command.getCourseImageFile() != null) {
                courseImageUrl = fileStoragePort.saveImageFile(command.getCourseImageFile());
            }

            CourseDetailResult courseDetailResult = coursePort.registerCourse(
                    command.getCourseSeriesId(),
                    command.getCourseTitle(),
                    command.getCourseSubTitle(),
                    command.getCoursePrice(),
                    courseImageUrl,
                    command.getCourseDescription(),
                    command.getCourseGenre(),
                    command.getSubjectCategory(),
                    CourseStatus.REGISTERED,
                    command.getExpiredAt()
            );

            Boolean isRecentCourseOpen = coursePort.registerRecentCourseOpen(
                    courseDetailResult.getCourseId(),
                    teacherId
            );

            coursePort.sendRecentCourseOpen(
                    courseDetailResult.getCourseId(),
                    teacherId,
                    ProductBroadcastingCategory.COURSE_UPLOAD
            );

            return CourseDetail.from(courseDetailResult, isRecentCourseOpen);
        }

        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
    }

    @Override
    public CourseDetail modifyCourse(ModifyCourseCommand command) {
        Long teacherId = teacherQueryPort.getTeacherIdByCourseId(command.getCourseId());
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), teacherId)) {
            String courseImageUrl = "";
            if (command.getCourseImageFile() != null) {
                courseImageUrl = fileStoragePort.saveImageFile(command.getCourseImageFile());
            }

            CourseDetailResult courseDetailResult = coursePort.modifyCourse(
                    command.getCourseId(),
                    command.getCourseSeriesId(),
                    command.getCourseTitle(),
                    command.getCourseSubTitle(),
                    command.getCoursePrice(),
                    courseImageUrl,
                    command.getCourseDescription(),
                    command.getCourseGenre(),
                    command.getSubjectCategory(),
                    command.getExpiredAt()
            );

            Boolean isRecentCourseOpen = coursePort.getRecentCourseOpen(command.getCourseId());

            return CourseDetail.from(courseDetailResult, isRecentCourseOpen);
        }

        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
    }

    @Override
    public void deleteCourse(DeleteCourseCommand command) {
        Long teacherId = teacherQueryPort.getTeacherIdByCourseId(command.getCourseId());
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), teacherId)) {
            coursePort.deleteCourse(command.getCourseId());
        } else {
            throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
        }
    }
}
