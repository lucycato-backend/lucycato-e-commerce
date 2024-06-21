package org.lucycato.productcommandservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productcommandservice.application.port.in.TeacherUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteTeacherCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyTeacherCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterTeacherCommand;
import org.lucycato.productcommandservice.application.port.out.FileStoragePort;
import org.lucycato.productcommandservice.application.port.out.TeacherPort;
import org.lucycato.productcommandservice.application.port.out.UserAuthPort;
import org.lucycato.productcommandservice.domain.TeacherDetail;
import org.lucycato.productcommandservice.domain.enums.TeacherStatus;
import org.lucycato.productcommandservice.error.ProductCommandErrorCodeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService implements TeacherUseCase {
    private final TeacherPort teacherPort;

    private final FileStoragePort fileStoragePort;

    private final UserAuthPort userAuthPort;

    @Override
    public TeacherDetail registerTeacher(RegisterTeacherCommand command) {
        String teacherImageUrl = "";
        if (command.getTeacherImageFile() != null) {
            teacherImageUrl = fileStoragePort.saveImageFile(command.getTeacherImageFile());
        }
        String teacherCurriculumImageUrl = "";
        if (command.getTeacherCurriculumImageFile() != null) {
            teacherCurriculumImageUrl = fileStoragePort.saveImageFile(command.getTeacherCurriculumImageFile());
        }

        String teacherCurriculumVideoUrl = "";
        if (command.getTeacherCurriculumVideoFile() != null) {
            teacherCurriculumVideoUrl = fileStoragePort.saveVideoFile(command.getTeacherCurriculumVideoFile());
        }

        return TeacherDetail.from(teacherPort.registerTeacher(
                command.getTeacherRank(),
                command.getTeacherName(),
                command.getTeacherSlogan(),
                command.getTeacherProfileDescription(),
                teacherImageUrl,
                teacherCurriculumImageUrl,
                teacherCurriculumVideoUrl,
                command.getTeachingGenre(),
                TeacherStatus.REGISTERED
        ));
    }

    @Override
    public TeacherDetail modifyTeacher(ModifyTeacherCommand command) {
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), command.getTeacherId())) {
            String teacherImageUrl = "";
            if (command.getTeacherImageFile() != null) {
                teacherImageUrl = fileStoragePort.saveImageFile(command.getTeacherImageFile());
            }
            String teacherCurriculumImageUrl = "";
            if (command.getTeacherCurriculumImageFile() != null) {
                teacherCurriculumImageUrl = fileStoragePort.saveImageFile(command.getTeacherCurriculumImageFile());
            }

            String teacherCurriculumVideoUrl = "";
            if (command.getTeacherCurriculumVideoFile() != null) {
                teacherCurriculumVideoUrl = fileStoragePort.saveVideoFile(command.getTeacherCurriculumVideoFile());
            }

            return TeacherDetail.from(teacherPort.modifyTeacher(
                    command.getTeacherId(),
                    command.getTeacherRank(),
                    command.getTeacherName(),
                    command.getTeacherSlogan(),
                    command.getTeacherProfileDescription(),
                    teacherImageUrl,
                    teacherCurriculumImageUrl,
                    teacherCurriculumVideoUrl,
                    command.getTeachingGenre()
            ));
        }
        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);

    }

    @Override
    public void deleteTeacher(DeleteTeacherCommand command) {
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), command.getTeacherId())) {
            teacherPort.removeTeacher(command.getTeacherId());
        } else {
            throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
        }
    }
}
