package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.productcommandservice.adapter.in.web.request.RegisterTextEBookRequest;
import org.lucycato.productcommandservice.application.port.in.TextEBooksUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteTextEBookCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyTeacherCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyTextEBookCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterTextEBookCommand;
import org.lucycato.productcommandservice.domain.TextEBookDetail;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class TextEBookController {
    private final TextEBooksUseCase textEBooksUseCase;

    @PostMapping(value = "api/admin/v1/text-e-books", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TextEBookDetail registerTextEBook(
            @RequestPart(name = "request")
            RegisterTextEBookRequest request,
            @RequestPart(name = "textEBookImageFile")
            MultipartFile textEBookImageFile,
            @RequestPart(name = "textEBookPreviewDownloadFile")
            MultipartFile textEBookPreviewDownloadFile,
            @RequestPart(name = "textEBookFullDownloadUrl")
            MultipartFile textEBookFullDownloadUrl

    ) {
        RegisterTextEBookCommand command = new RegisterTextEBookCommand(
                request.getCourseId(),
                request.getTextEBookUniqueCode(),
                request.getTextEBookTitle(),
                request.getTextEBookDescription(),
                request.getTextEBookTableOfContents(),
                request.getTextEBookAuthor(),
                request.getTextEBookPublisher(),
                request.getTextEBookPage(),
                request.getSubjectCategory(),
                request.getTextEBookPublishedAt(),
                textEBookImageFile,
                textEBookPreviewDownloadFile,
                textEBookFullDownloadUrl
        );

        return textEBooksUseCase.registerTextEBook(command);
    }

    @PatchMapping("api/admin/v1/text-e-books/{textEBookId}")
    public TextEBookDetail modifyTextEBook(
            @PathVariable
            Long textEBookId,
            @RequestPart(name = "request")
            RegisterTextEBookRequest request,
            @RequestPart(name = "textEBookImageFile")
            MultipartFile textEBookImageFile,
            @RequestPart(name = "textEBookPreviewDownloadFile")
            MultipartFile textEBookPreviewDownloadFile,
            @RequestPart(name = "textEBookFullDownloadUrl")
            MultipartFile textEBookFullDownloadUrl
    ) {
        ModifyTextEBookCommand command = new ModifyTextEBookCommand(
                textEBookId,
                request.getCourseId(),
                request.getTextEBookUniqueCode(),
                request.getTextEBookTitle(),
                request.getTextEBookDescription(),
                request.getTextEBookTableOfContents(),
                request.getTextEBookAuthor(),
                request.getTextEBookPublisher(),
                request.getTextEBookPage(),
                request.getSubjectCategory(),
                request.getTextEBookPublishedAt(),
                textEBookImageFile,
                textEBookPreviewDownloadFile,
                textEBookFullDownloadUrl
        );

        return textEBooksUseCase.modifyTextEBook(command);
    }

    @DeleteMapping("api/admin/v1/text-e-books/{textEBookId}")
    public Object deleteTextEBook(
            @PathVariable
            Long textEBookId
    ) {
        DeleteTextEBookCommand command = new DeleteTextEBookCommand(textEBookId);
        textEBooksUseCase.deleteTextEBook(command);

        return new Object();
    }
}
