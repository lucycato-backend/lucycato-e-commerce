package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.productcommandservice.adapter.in.web.request.RegisterTextEBookRequest;
import org.lucycato.productcommandservice.application.port.in.TextEBooksUseCase;
import org.lucycato.productcommandservice.application.port.in.command.DeleteTextEBookCommand;
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
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @RequestPart(name = "request")
            RegisterTextEBookRequest request,
            @RequestPart(name = "textEBookImageFile", required = false)
            MultipartFile textEBookImageFile,
            @RequestPart(name = "textEBookPreviewDownloadFile", required = false)
            MultipartFile textEBookPreviewDownloadFile,
            @RequestPart(name = "textEBookFullDownloadFile", required = false)
            MultipartFile textEBookFullDownloadFile

    ) {
        RegisterTextEBookCommand command = new RegisterTextEBookCommand(
                adminUserHeaderDetail.getAdminUserId(),
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
                textEBookFullDownloadFile
        );

        return textEBooksUseCase.registerTextEBook(command);
    }

    @PatchMapping("api/admin/v1/text-e-books/{textEBookId}")
    public TextEBookDetail modifyTextEBook(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long textEBookId,
            @RequestPart(name = "request")
            RegisterTextEBookRequest request,
            @RequestPart(name = "textEBookImageFile", required = false)
            MultipartFile textEBookImageFile,
            @RequestPart(name = "textEBookPreviewDownloadFile", required = false)
            MultipartFile textEBookPreviewDownloadFile,
            @RequestPart(name = "textEBookFullDownloadFile", required = false)
            MultipartFile textEBookFullDownloadFile
    ) {
        ModifyTextEBookCommand command = new ModifyTextEBookCommand(
                adminUserHeaderDetail.getAdminUserId(),
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
                textEBookFullDownloadFile
        );

        return textEBooksUseCase.modifyTextEBook(command);
    }

    @DeleteMapping("api/admin/v1/text-e-books/{textEBookId}")
    public Object deleteTextEBook(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long textEBookId
    ) {
        DeleteTextEBookCommand command = new DeleteTextEBookCommand(
                adminUserHeaderDetail.getAdminUserId(),
                textEBookId
        );
        textEBooksUseCase.deleteTextEBook(command);

        return new Object();
    }
}
