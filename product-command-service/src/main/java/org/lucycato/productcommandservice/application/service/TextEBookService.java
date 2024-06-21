package org.lucycato.productcommandservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productcommandservice.application.port.in.TextEBooksUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteTextEBookCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyTextEBookCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterTextEBookCommand;
import org.lucycato.productcommandservice.application.port.out.*;
import org.lucycato.productcommandservice.domain.LectureDetail;
import org.lucycato.productcommandservice.domain.TextEBookDetail;
import org.lucycato.productcommandservice.domain.enums.LectureStatus;
import org.lucycato.productcommandservice.domain.enums.TextEBookStatus;
import org.lucycato.productcommandservice.error.ProductCommandErrorCodeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TextEBookService implements TextEBooksUseCase {
    private final TextEBookPort textEBookPort;

    private final FileStoragePort fileStoragePort;

    private final UserAuthPort userAuthPort;

    private final TeacherQueryPort teacherQueryPort;

    @Override
    public TextEBookDetail registerTextEBook(RegisterTextEBookCommand command) {
        Long teacherId = teacherQueryPort.getTeacherIdByCourseId(command.getCourseId());
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), teacherId)) {
            String textEBookImageUrl = "";
            if (command.getTextEBookImageFile() != null) {
                textEBookImageUrl = fileStoragePort.saveImageFile(command.getTextEBookImageFile());
            }
            String textEBookPreviewDownloadUrl = "";
            if (command.getTextEBookPreviewDownloadFile() != null) {
                textEBookPreviewDownloadUrl = fileStoragePort.savePdfFile(command.getTextEBookPreviewDownloadFile());
            }

            String textEBookFullDownloadUrl = "";
            if (command.getTextEBookFullDownloadUrl() != null) {
                textEBookFullDownloadUrl = fileStoragePort.savePdfFile(command.getTextEBookFullDownloadUrl());
            }

            return TextEBookDetail.from(textEBookPort.registerTextEBook(
                    command.getCourseId(),
                    command.getTextEBookUniqueCode(),
                    textEBookImageUrl,
                    command.getTextEBookTitle(),
                    command.getTextEBookDescription(),
                    command.getTextEBookTableOfContents(),
                    command.getTextEBookAuthor(),
                    command.getTextEBookPublisher(),
                    textEBookPreviewDownloadUrl,
                    textEBookFullDownloadUrl,
                    command.getTextEBookPage(),
                    command.getSubjectCategory(),
                    TextEBookStatus.OPERATOR,
                    command.getTextEBookPublishedAt()
            ));
        }

        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
    }

    @Override
    public TextEBookDetail modifyTextEBook(ModifyTextEBookCommand command) {
        Long teacherId = teacherQueryPort.getTeacherIdByCourseId(command.getCourseId());
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), teacherId)) {
            String textEBookImageUrl = "";
            if (command.getTextEBookImageFile() != null) {
                textEBookImageUrl = fileStoragePort.saveImageFile(command.getTextEBookImageFile());
            }
            String textEBookPreviewDownloadUrl = "";
            if (command.getTextEBookPreviewDownloadFile() != null) {
                textEBookPreviewDownloadUrl = fileStoragePort.savePdfFile(command.getTextEBookPreviewDownloadFile());
            }

            String textEBookFullDownloadUrl = "";
            if (command.getTextEBookFullDownloadUrl() != null) {
                textEBookFullDownloadUrl = fileStoragePort.savePdfFile(command.getTextEBookFullDownloadUrl());
            }

            return TextEBookDetail.from(textEBookPort.modifyTextEBook(
                    command.getTextEBookId(),
                    command.getCourseId(),
                    command.getTextEBookUniqueCode(),
                    textEBookImageUrl,
                    command.getTextEBookTitle(),
                    command.getTextEBookDescription(),
                    command.getTextEBookTableOfContents(),
                    command.getTextEBookAuthor(),
                    command.getTextEBookPublisher(),
                    textEBookPreviewDownloadUrl,
                    textEBookFullDownloadUrl,
                    command.getTextEBookPage(),
                    command.getSubjectCategory(),
                    command.getTextEBookPublishedAt()
            ));
        }

        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
    }

    @Override
    public void deleteTextEBook(DeleteTextEBookCommand command) {
        Long teacherId = teacherQueryPort.getTeacherIdByTextEBookId(command.getTextEBookId());
        if (userAuthPort.checkAuthToChangeTeacher(command.getRequestAdminUserId(), teacherId)) {
            textEBookPort.removeTextEBook(command.getTextEBookId());
        }
        throw new ApiExceptionImpl(ProductCommandErrorCodeImpl.ADMIN_USER_NOT_CHANGE_TO_TEACHER);
    }
}
