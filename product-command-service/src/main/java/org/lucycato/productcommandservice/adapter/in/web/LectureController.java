package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productcommandservice.adapter.in.web.request.RegisterLectureRequest;
import org.lucycato.productcommandservice.application.port.in.LectureUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteLectureCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyLectureCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterLectureCommand;
import org.lucycato.productcommandservice.domain.LectureDetail;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class LectureController {
    private final LectureUseCase lectureUseCase;

    @PostMapping(value = "api/admin/v1/lectures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LectureDetail registerLecture(
            @RequestPart(name = "request")
            RegisterLectureRequest request,
            @RequestPart(name = "lectureThumbnailImageFile")
            MultipartFile lectureThumbnailImageFile,
            @RequestPart(name = "lectureVideoFile")
            MultipartFile lectureVideoFile
    ) {
        RegisterLectureCommand command = new RegisterLectureCommand(
                request.getCourseId(),
                request.getLectureTitle(),
                request.getLectureDescription(),
                request.getLectureCategory(),
                lectureThumbnailImageFile,
                lectureVideoFile
        );

        return lectureUseCase.registerLecture(command);
    }

    @PatchMapping(value = "api/admin/v1/lectures/{lectureId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LectureDetail modifyLecture(
            @PathVariable
            Long lectureId,
            @RequestPart(name = "request")
            RegisterLectureRequest request,
            @RequestPart(name = "lectureThumbnailImageFile")
            MultipartFile lectureThumbnailImageFile,
            @RequestPart(name = "lectureVideoFile")
            MultipartFile lectureVideoFile
    ) {
        ModifyLectureCommand command = new ModifyLectureCommand(
                lectureId,
                request.getCourseId(),
                request.getLectureTitle(),
                request.getLectureDescription(),
                request.getLectureCategory(),
                lectureThumbnailImageFile,
                lectureVideoFile
        );

        return lectureUseCase.modifyLecture(command);

    }

    @DeleteMapping("api/admin/v1/lectures/{lectureId}")
    public Object deleteLecture(
            @PathVariable
            Long lectureId
    ) {
        DeleteLectureCommand command = new DeleteLectureCommand(lectureId);
        lectureUseCase.deleteLecture(command);

        return new Object();
    }
}
