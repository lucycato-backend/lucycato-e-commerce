package org.lucycato.productservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.productservice.application.port.in.ProductRegisteredUseCase;
import org.lucycato.productservice.application.port.in.command.*;
import org.lucycato.productservice.application.port.out.FileStorePort;
import org.lucycato.productservice.application.port.out.LecturePort;
import org.lucycato.productservice.application.port.out.LectureTextEBookPort;
import org.lucycato.productservice.application.port.out.TeacherPort;
import org.lucycato.productservice.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductRegisteredService implements ProductRegisteredUseCase {
    private final TeacherPort teacherPort;
    private final FileStorePort fileStorePort;
    private final LectureTextEBookPort lectureTextEBookPort;
    private final LecturePort lecturePort;

    @Override
    public Mono<Teacher> registerTeacher(RegisteredTeacherCommand command) {
        return teacherPort.registerTeacher(
                        command.getTeacherName(),
                        command.getSlogan(),
                        command.getProfileDescription(),
                        command.getTeachingGenre()
                )
                .flatMap(teacherResult -> Mono.zip(
                                Mono.just(teacherResult),
                                fileStorePort.saveImageFiles(command.getTeacherImageFiles()),
                                fileStorePort.saveImageFile(command.getCurriculumImageFile()),
                                fileStorePort.saveVideoFile(command.getCurriculumVideoFile())
                        )
                )
                .flatMap(tuple -> teacherPort.modifyTeacher(
                        tuple.getT1().getTeacherId(),
                        tuple.getT2(),
                        tuple.getT3(),
                        tuple.getT4()
                ))
                .map(Teacher::from);
    }

    //TODO
    @Override
    public Mono<LectureSeries> registerLectureSeries(RegisteredLectureSeriesCommand command) {
        return null;
    }

    //TODO:
    @Override
    public Mono<Lecture> registerLecture(RegisteredLectureCommand command) {
        return null;
    }

    //TODO:
    @Override
    public Mono<LectureContent> registerLectureContent(RegisteredLectureContentCommand command) {
        return null;
    }

    //TODO: 밍디
    @Override
    public Mono<LectureTextEBook> registerLectureTextEBook(RegisteredLectureTextEBookCommand command) {
        return lecturePort.getLectureById(command.getLectureId())
                .flatMap(lecture -> lectureTextEBookPort.registerLectureTextEBook(
                                command.getLectureId(),
                                command.getEBookUniqueCode(),
                                command.getTitle(),
                                command.getDescription(),
                                command.getTableOfContents(),
                                command.getAuthor(),
                                command.getPublisher(),
                                command.getPage(),
                                lecture.getSubjectCategory(),
                                lecture.getTeachingGenre()
                        )
                        .flatMap(lectureTextEBook -> Mono.zip(
                                        Mono.just(lectureTextEBook),
                                        fileStorePort.saveImageFiles(command.getLectureTextEBookImageFiles()),
                                        fileStorePort.saveImageFile(command.getPreviewTextEBookPDFFile()),
                                        fileStorePort.saveImageFile(command.getFullTextEBookPDFFile())
                                )
                        )
                        .flatMap(tuple -> lectureTextEBookPort.modifyLectureTextEBook(
                                tuple.getT1().getLectureTextEBookId(),
                                tuple.getT2(),
                                tuple.getT3(),
                                tuple.getT4()
                        ))
                        .map(LectureTextEBook::from));
    }

    //TODO:
    @Override
    public Mono<TeacherNews> registerTeacherNews(RegisteredTeacherNewsCommand command) {
        return null;
    }

    //TODO:
    @Override
    public Mono<LectureReview> registerTeacherReview(RegisteredLectureReviewCommand command) {
        return null;
    }
}
