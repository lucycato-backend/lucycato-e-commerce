package org.lucycato.productservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.productservice.application.port.in.ProductRegisteredUseCase;
import org.lucycato.productservice.application.port.in.command.*;
import org.lucycato.productservice.application.port.out.FileStorePort;
import org.lucycato.productservice.application.port.out.LectureContentPort;
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
    private final LectureContentPort lectureContentPort;

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

    //TODO: 진영 - 강의 내용 등록하는 비즈니스 로직 짜기
    @Override
    public Mono<LectureContent> registerLectureContent(RegisteredLectureContentCommand command) {
        // LectureContentPort 통해(persistence계층어댑터와 연결되겠지) register하기
        return lectureContentPort.registerLectureContent(
                        command.getLectureId(),
                        command.getLectureContentTitle(),
                        command.getLectureContentCategory()
                )
                .flatMap(lectureContentResult -> Mono.zip(
                        Mono.just(lectureContentResult),
                        fileStorePort.saveImageFile(command.getLectureContentThumbnailImageFile()),
                        fileStorePort.saveVideoFile(command.getLectureContentVideoFile())
                ))
                .flatMap(tuple -> lectureContentPort.modifyLectureContent(
                        tuple.getT1().getLectureContentId(),
                        tuple.getT2(),
                        tuple.getT3()
                ))
                .map(LectureContent::from);

    }

    //TODO:
    @Override
    public Mono<LectureTextEBook> registerLectureTextEBook(RegisteredLectureTextEBookCommand command) {
        return null;
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
