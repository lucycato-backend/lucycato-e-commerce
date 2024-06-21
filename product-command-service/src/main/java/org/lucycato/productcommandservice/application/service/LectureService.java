package org.lucycato.productcommandservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productcommandservice.application.port.in.LectureUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteLectureCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyLectureCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterLectureCommand;
import org.lucycato.productcommandservice.application.port.out.*;
import org.lucycato.productcommandservice.application.port.out.result.LectureDetailResult;
import org.lucycato.productcommandservice.domain.CourseSeriesDetail;
import org.lucycato.productcommandservice.domain.LectureDetail;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productcommandservice.domain.enums.LectureStatus;
import org.lucycato.productcommandservice.error.ProductCommandErrorCodeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureService implements LectureUseCase {
    private final LecturePort lecturePort;

    private final FileStoragePort fileStoragePort;

    private final UserAuthPort userAuthPort;

    private final TeacherQueryPort teacherQueryPort;

    @Override
    public LectureDetail registerLecture(RegisterLectureCommand command) {
        Long teacherId = teacherQueryPort.getTeacherIdByCourseId(command.getCourseId());
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), teacherId)) {
            String lectureThumbnailImageUrl = "";
            if (command.getLectureThumbnailImageFile() != null) {
                lectureThumbnailImageUrl = fileStoragePort.saveImageFile(command.getLectureThumbnailImageFile());
            }
            String lectureVideoUrl = "";
            if (command.getLectureVideoFile() != null) {
                lectureVideoUrl = fileStoragePort.saveVideoFile(command.getLectureVideoFile());
            }


            return LectureDetail.from(lecturePort.registerLecture(
                    command.getCourseId(),
                    command.getLectureTitle(),
                    command.getLectureDescription(),
                    lectureThumbnailImageUrl,
                    lectureVideoUrl,
                    command.getLectureCategory(),
                    LectureStatus.REGISTERED
            ));
        }

        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
    }

    @Override
    public LectureDetail modifyLecture(ModifyLectureCommand command) {
        Long teacherId = teacherQueryPort.getTeacherIdByCourseId(command.getCourseId());
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), teacherId)) {
            String lectureThumbnailImageUrl = "";
            if (command.getLectureThumbnailImageFile() != null) {
                lectureThumbnailImageUrl = fileStoragePort.saveImageFile(command.getLectureThumbnailImageFile());
            }
            String lectureVideoUrl = "";
            if (command.getLectureVideoFile() != null) {
                lectureVideoUrl = fileStoragePort.saveVideoFile(command.getLectureVideoFile());
            }


            return LectureDetail.from(lecturePort.modifyLecture(
                    command.getLectureId(),
                    command.getCourseId(),
                    command.getLectureTitle(),
                    command.getLectureDescription(),
                    lectureThumbnailImageUrl,
                    lectureVideoUrl,
                    command.getLectureCategory()
            ));
        }

        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
    }

    @Override
    public void deleteLecture(DeleteLectureCommand command) {
        Long teacherId = teacherQueryPort.getTeacherIdByLectureId(command.getLectureId());
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), teacherId)) {
            lecturePort.deleteLecture(command.getLectureId());
        }
        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
    }
}
