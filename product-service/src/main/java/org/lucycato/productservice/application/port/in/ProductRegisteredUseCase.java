package org.lucycato.productservice.application.port.in;

import org.lucycato.productservice.adapter.in.web.request.RegisteredLectureReviewRequest;
import org.lucycato.productservice.adapter.in.web.request.RegisteredTeacherNewsRequest;
import org.lucycato.productservice.application.port.in.command.*;
import org.lucycato.productservice.domain.*;
import reactor.core.publisher.Mono;

public interface ProductRegisteredUseCase {
    Mono<Teacher> registerTeacher(RegisteredTeacherCommand command);

    Mono<LectureSeries> registerLectureSeries(RegisteredLectureSeriesCommand command);

    Mono<Lecture> registerLecture(RegisteredLectureCommand command);

    Mono<LectureContent> registerLectureContent(RegisteredLectureContentCommand command);

    Mono<LectureTextEBook> registerLectureTextEBook(RegisteredLectureTextEBookCommand command);

    Mono<TeacherNews> registerTeacherNews(RegisteredTeacherNewsCommand command);

    Mono<LectureReview> registerTeacherReview(RegisteredLectureReviewCommand command);
}
