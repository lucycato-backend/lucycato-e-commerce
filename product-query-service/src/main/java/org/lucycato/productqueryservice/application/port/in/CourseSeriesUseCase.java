package org.lucycato.productqueryservice.application.port.in;

import org.lucycato.productqueryservice.application.port.in.command.CourseSeriesDetailSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.SpecificCourseSeriesCourseSearchByTeacherIdCommand;
import org.lucycato.productqueryservice.application.port.in.command.SpecificCourseSeriesCourseSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.SpecificCourseSeriesTextEBookSearchCommand;
import org.lucycato.productqueryservice.domain.CourseSeriesCourse;
import org.lucycato.productqueryservice.domain.CourseSeriesDetail;
import org.lucycato.productqueryservice.domain.CourseSeriesTextEBook;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseSeriesUseCase {
    Mono<CourseSeriesDetail> getCourseSeries(CourseSeriesDetailSearchCommand command);

    Flux<CourseSeriesCourse> getCourseSeriesCourseList(SpecificCourseSeriesCourseSearchCommand command);

    Flux<CourseSeriesCourse> getCourseSeriesCourseListByTeacherId(SpecificCourseSeriesCourseSearchByTeacherIdCommand command);

    Flux<CourseSeriesTextEBook> getCourseSeriesTextEBookList(SpecificCourseSeriesTextEBookSearchCommand command);
}
