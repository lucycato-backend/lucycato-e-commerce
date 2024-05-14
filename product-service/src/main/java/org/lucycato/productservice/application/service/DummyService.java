package org.lucycato.productservice.application.service;

import org.lucycato.productservice.application.port.in.*;
import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByIdsCommand;
import org.lucycato.productservice.application.port.in.command.ByTeachingGenreCommand;
import org.lucycato.productservice.domain.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DummyService implements LectureContentQueryUseCase, LectureQueryUseCase, LectureReviewQueryUseCase, LectureSeriesQueryUseCase, LectureTextEBookQueryUseCase, TeacherNewsQueryUseCase, TeacherQueryUseCase {
    @Override
    public Flux<LectureContent.Record> getLectureContentList() {
        return null;
    }

    @Override
    public Flux<LectureContent.Record> getLectureContentByLectureIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Mono<LectureContent> getLectureContentById(ByIdCommand command) {
        return null;
    }

    @Override
    public Flux<Lecture.Record> getLectureList() {
        return null;
    }

    @Override
    public Flux<Lecture.Record> getLectureListByTargetTeacherIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Flux<Lecture.Record> getLectureListByTargetLectureSeriesIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Mono<Lecture> getLectureById(ByIdCommand command) {
        return null;
    }

    @Override
    public Flux<LectureReview.Record> getLectureReviewList() {
        return null;
    }

    @Override
    public Flux<LectureReview.Record> getLectureReviewListByTargetTeacherIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Flux<LectureReview.Record> getLectureReviewListByTargetLectureIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Mono<LectureReview> getLectureReviewById(ByIdCommand command) {
        return null;
    }

    @Override
    public Flux<LectureSeries.Record> getLectureSeriesList() {
        return null;
    }

    @Override
    public Flux<LectureSeries.Record> getLectureSeriesListByTargetTeacherIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Mono<LectureSeries> getLectureSeriesById(ByIdCommand command) {
        return null;
    }

    @Override
    public Flux<LectureTextEBook> getLectureTextEBookDetailList() {
        return null;
    }

    @Override
    public Flux<LectureTextEBook> getLectureTextEBookDetailListByTargetTeacherIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Flux<LectureTextEBook> getLectureTextEBookDetailListByTargetLectureIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Flux<LectureTextEBook.Record> getLectureTextEBookList() {
        return null;
    }

    @Override
    public Flux<LectureTextEBook.Record> getLectureTextEBookListByTargetTeacherIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Flux<LectureTextEBook.Record> getLectureTextEBookListByTargetLectureIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Mono<LectureTextEBook> getLectureTextEBookById(ByIdCommand command) {
        return null;
    }

    @Override
    public Flux<TeacherNews.Record> getTeacherNewsList() {
        return null;
    }

    @Override
    public Flux<TeacherNews.Record> getTeacherNewsListByTargetTeacherIds(ByIdsCommand command) {
        return null;
    }

    @Override
    public Mono<TeacherNews> getTeacherNewsById(ByIdCommand command) {
        return null;
    }

    @Override
    public Flux<Teacher.Record> getTeacherList() {
        return null;
    }

    @Override
    public Flux<Teacher.Record> getTeacherListByTargetTeacherIds(ByTeachingGenreCommand command) {
        return null;
    }

    @Override
    public Mono<Teacher> getTeacherById(ByIdCommand command) {
        return null;
    }
}
