package org.lucycato.productqueryservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productqueryservice.application.port.in.CourseUseCase;
import org.lucycato.productqueryservice.application.port.in.command.*;
import org.lucycato.productqueryservice.application.port.out.*;
import org.lucycato.productqueryservice.domain.CourseDetail;
import org.lucycato.productqueryservice.domain.CourseLecture;
import org.lucycato.productqueryservice.domain.CourseTextEBook;
import org.lucycato.productqueryservice.error.ProductErrorCodeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService implements CourseUseCase {
    private final CoursePort coursePort;

    private final LecturePort lecturePort;

    private final TextEBookPort textEBookPort;

    private final UserAuthPort userAuthPort;

    private final OrderPort orderPort;

    @Override
    public Mono<CourseDetail> getCurses(CourseDetailSearchCommand command) {
        return coursePort.getCourseByCourseId(command.getCourseId())
                .switchIfEmpty(Mono.error(new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND)))
                .flatMap(courseDetailResult ->
                        coursePort.checkRecentCourseOpenListByCourseIds(Collections.singletonList(courseDetailResult.getCourseId()))
                                .collectList()
                                .map(checkedRecentCourseOpenResultList -> CourseDetail.from(courseDetailResult, checkedRecentCourseOpenResultList.isEmpty()))
                );
    }

    @Override
    public Flux<CourseLecture> getCourseLectureList(SpecificCourseLectureSearchCommand command) {
        return lecturePort.getLectureListByCourseIds(Collections.singletonList(command.getCourseId()))
                .flatMap(lectureResult -> coursePort.getSimpleCourseByCourseId(lectureResult.getCourseId()).cache()
                        .map(courseResult -> CourseLecture.from(courseResult, lectureResult))
                )
                .sort((before, after) -> Long.compare(after.getLectureId(), before.getLectureId()));
    }

    @Override
    public Flux<CourseLecture> getAuthCourseLectureList(SpecificAdminCourseLectureSearchCommand command) {
        return userAuthPort.checkAuthToChangeTeacher(command.getAdminUserId())
                .filter(isAuth -> isAuth)
                .switchIfEmpty(Mono.error(new ApiExceptionImpl(ProductErrorCodeImpl.ADMIN_USER_NOT_TEACHER_ASSISTANCE)))
                .flatMapMany(it -> lecturePort.getLectureListByCourseIds(Collections.singletonList(command.getCourseId()))
                        .flatMap(lectureResult -> coursePort.getSimpleCourseByCourseId(lectureResult.getCourseId()).cache()
                                .map(courseResult -> CourseLecture.from(courseResult, lectureResult))
                        ))
                .sort((before, after) -> Long.compare(after.getLectureId(), before.getLectureId()));
    }

    @Override
    public Flux<CourseLecture> getAuthCourseLectureList(SpecificAppCourseLectureSearchCommand command) {
        return orderPort.checkAppUserBuyCourse(command.getAppUserId(), command.getCourseId())
                .filter(isBuy -> isBuy)
                .switchIfEmpty(Mono.error(new ApiExceptionImpl(ProductErrorCodeImpl.APP_USER_NOT_FOUND_BUY_COURSE)))
                .flatMapMany(it -> lecturePort.getLectureListByCourseIds(Collections.singletonList(command.getCourseId()))
                        .flatMap(lectureResult -> coursePort.getSimpleCourseByCourseId(lectureResult.getCourseId()).cache()
                                .map(courseResult -> CourseLecture.from(courseResult, lectureResult))
                        ))
                .sort((before, after) -> Long.compare(after.getLectureId(), before.getLectureId()));
    }

    @Override
    public Flux<CourseTextEBook> getCourseTextEBookList(SpecificCourseTextEBookSearchCommand command) {
        return textEBookPort.getTextEBookListByCourseIds(Collections.singletonList(command.getCourseId()))
                .flatMap(textEBookResult -> coursePort.getSimpleCourseByCourseId(textEBookResult.getCourseId()).cache()
                        .map(courseResult -> CourseTextEBook.from(courseResult, textEBookResult))
                )
                .sort((before, after) -> Long.compare(after.getTextEBookId(), before.getTextEBookId()));
    }

    @Override
    public Flux<CourseTextEBook> getAuthCourseTextEBookList(SpecificAdminCourseTextEBookSearchCommand command) {
        return userAuthPort.checkAuthToChangeTeacher(command.getAdminUserId())
                .filter(isAuth -> isAuth)
                .switchIfEmpty(Mono.error(new ApiExceptionImpl(ProductErrorCodeImpl.APP_USER_NOT_FOUND_BUY_COURSE)))
                .flatMapMany(it -> textEBookPort.getTextEBookListByCourseIds(Collections.singletonList(command.getCourseId()))
                        .flatMap(textEBookResult -> coursePort.getSimpleCourseByCourseId(textEBookResult.getCourseId()).cache()
                                .map(courseResult -> CourseTextEBook.from(courseResult, textEBookResult))
                        ))
                .sort((before, after) -> Long.compare(after.getTextEBookId(), before.getTextEBookId()));
    }

    @Override
    public Flux<CourseTextEBook> getAuthCourseTextEBookList(SpecificAppCourseTextEBookSearchCommand command) {
        return orderPort.checkAppUserBuyCourse(command.getAppUserId(), command.getCourseId())
                .filter(isBuy -> isBuy)
                .switchIfEmpty(Mono.error(new ApiExceptionImpl(ProductErrorCodeImpl.APP_USER_NOT_FOUND_BUY_COURSE)))
                .flatMapMany(it -> textEBookPort.getTextEBookListByCourseIds(Collections.singletonList(command.getCourseId()))
                        .flatMap(textEBookResult -> coursePort.getSimpleCourseByCourseId(textEBookResult.getCourseId()).cache()
                                .map(courseResult -> CourseTextEBook.from(courseResult, textEBookResult))
                        ))
                .sort((before, after) -> Long.compare(after.getTextEBookId(), before.getTextEBookId()));
    }
}
