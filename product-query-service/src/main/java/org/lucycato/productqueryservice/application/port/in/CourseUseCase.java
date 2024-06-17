package org.lucycato.productqueryservice.application.port.in;

import org.lucycato.productqueryservice.application.port.in.command.*;
import org.lucycato.productqueryservice.domain.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseUseCase {
    Mono<CourseDetail> getCurses(CourseDetailSearchCommand command);

    Flux<CourseLecture> getCourseLectureList(SpecificCourseLectureSearchCommand command);

    Flux<CourseLecture> getAuthCourseLectureList(SpecificAdminCourseLectureSearchCommand command);

    Flux<CourseLecture> getAuthCourseLectureList(SpecificAppCourseLectureSearchCommand command);

    Flux<CourseTextEBook> getCourseTextEBookList(SpecificCourseTextEBookSearchCommand command);

    Flux<CourseTextEBook> getAuthCourseTextEBookList(SpecificAdminCourseTextEBookSearchCommand command);

    Flux<CourseTextEBook> getAuthCourseTextEBookList(SpecificAppCourseTextEBookSearchCommand command);
}
