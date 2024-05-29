package org.lucycato.productservice.adapter.in.web;


import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.lucycato.productservice.adapter.in.web.request.*;
import org.lucycato.productservice.application.port.in.ProductRegisteredUseCase;
import org.lucycato.productservice.application.port.in.command.*;
import org.lucycato.productservice.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ProductRegisteredController {
    private final ProductRegisteredUseCase productRegisteredUseCase;

    @PostMapping(value = "api/lucycato/v1/admin/teacher/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Teacher> registerTeacher(
            @RequestPart(name = "request")
            RegisteredTeacherRequest request,
            @RequestPart(name = "teacherImageFiles", required = false)
            List<MultipartFile> teacherImageFiles,
            @RequestPart(name = "curriculumImageFile", required = false)
            MultipartFile curriculumImageFile,
            @RequestPart(name = "curriculumVideoFile", required = false)
            MultipartFile curriculumVideoFile
    ) {
        RegisteredTeacherCommand command = new RegisteredTeacherCommand(
                request.getTeacherName(),
                request.getSlogan(),
                request.getProfileDescription(),
                request.getTeachingGenre(),
                teacherImageFiles,
                curriculumImageFile,
                curriculumVideoFile
        );
        return productRegisteredUseCase.registerTeacher(command);
    }

    //TODO: 경서님
    @PostMapping(value = "api/lucycato/v1/admin/lecture-series/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<LectureSeries> registerLectureSeries(
            @RequestPart(name = "request")
            RegisteredLectureSeriesRequest request,
            @RequestPart(name = "lectureSeriesExplainImageFiles", required = false)
            List<MultipartFile> files
    ) {
        RegisteredLectureSeriesCommand command = new RegisteredLectureSeriesCommand(
                request.getTeacherId(),
                request.getLectureSeriesTitle(),
                request.getLectureSeriesDescription(),
                files
        );
        return productRegisteredUseCase.registerLectureSeries(command);
    }

    //TODO: 민우님
    @PostMapping(value = "api/lucycato/v1/admin/lecture/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Lecture> registerLecture(
            @RequestPart(name = "request")
            RegisteredLectureRequest request,
            @RequestPart(name = "lectureImageFile", required = false)
            MultipartFile file
    ) {
        RegisteredLectureCommand command = new RegisteredLectureCommand(
                request.getLectureSeriesId(),
                request.getLectureTitle(),
                request.getLectureSubTitle(),
                request.getLecturePrice(),
                request.getLectureDescription(),
                request.getLectureComposition(),
                request.getLectureTargetStudentCategories(),
                request.getLectureGenre(),
                request.getExpiredAt(),
                file
        );
        return productRegisteredUseCase.registerLecture(command);
    }
    //TODO: 진영님
    @PostMapping(value = "api/product/v1/admin/lecture-content/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<LectureContent> registerLectureContent(
            @RequestPart(name = "request")
            RegisteredLectureContentRequest request,
            @RequestPart(name = "lectureContentThumbnailImageUrl", required = false)
            MultipartFile lectureContentThumbnailImageFile,
            @RequestPart(name = "lectureContentVideoUrl", required = false)
            MultipartFile lectureContentVideoFile
    ) {
        RegisteredLectureContentCommand command = new RegisteredLectureContentCommand(
                request.getLectureId(),
                request.getLectureContentTitle(),
                request.getLectureContentCategory(),
                lectureContentThumbnailImageFile,
                lectureContentVideoFile
        );
        return productRegisteredUseCase.registerLectureContent(command);
    }

    //TODO: 민지님
    @PostMapping(value = "api/lucycato/v1/admin/lecture-text-e-book/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<LectureTextEBook> registerLectureTextEBook(
            @RequestPart(name = "request")
            RegisteredLectureTextEBookRequest request,
            @RequestPart(name = "lectureTextEBookImageFiles", required = false)
            List<MultipartFile> lectureTextEBookImageFiles,
            @RequestPart(name = "previewTextEBookPDFFile", required = false)
            MultipartFile previewTextEBookPDFFile,
            @RequestPart(name = "fullTextEBookPDFFile", required = false)
            MultipartFile fullTextEBookPDFFile
    ) {
        RegisteredLectureTextEBookCommand command = new RegisteredLectureTextEBookCommand(
                request.getLectureId(),
                request.getEBookUniqueCode(),
                request.getTitle(),
                request.getDescription(),
                request.getTableOfContents(),
                request.getAuthor(),
                request.getPublisher(),
                request.getPage(),
                lectureTextEBookImageFiles,
                previewTextEBookPDFFile,
                fullTextEBookPDFFile
        );
        return productRegisteredUseCase.registerLectureTextEBook(command);
    }

    //TODO: 석범님
    @PostMapping("api/lucycato/v1/admin/teacher-news/register")
    public Mono<TeacherNews> registerTeacherNews(
            @RequestBody
            RegisteredTeacherNewsRequest request
    ) {
        RegisteredTeacherNewsCommand command = new RegisteredTeacherNewsCommand(
                request.getTeacherId(),
                request.getTitle(),
                request.getContent(),
                request.getTeacherNewStatus()
        );
        return productRegisteredUseCase.registerTeacherNews(command);
    }

    //TODO: 민우님
    @PostMapping("api/lucycato/v1/app/lecture-review/register")
    public Mono<LectureReview> registerLectureReview(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail,
            @RequestBody
            RegisteredLectureReviewRequest request
    ) {
        RegisteredLectureReviewCommand command = new RegisteredLectureReviewCommand(
                appUserHeaderDetail.getAppUserId(),
                request.getLectureId(),
                request.getReviewTitle(),
                request.getReviewContent()
        );
        return productRegisteredUseCase.registerTeacherReview(command);
    }
}
