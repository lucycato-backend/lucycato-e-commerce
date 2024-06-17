package org.lucycato.productqueryservice.application.port.in;


import org.lucycato.productqueryservice.application.port.in.command.*;
import org.lucycato.productqueryservice.domain.Teacher;
import org.lucycato.productqueryservice.domain.TeacherCourseSeries;
import org.lucycato.productqueryservice.domain.TeacherDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherUseCase {
    Flux<Teacher> getTeacherList(TeacherSearchCommand command);

    Mono<TeacherDetail> getTeacher(TeacherDetailSearchCommand command);

    Flux<TeacherCourseSeries> getTeacherCourseSeriesList(TeacherCourseSeriesSearchCommand command);

    Flux<TeacherCourseSeries> getTeacherCourseSeriesList(SpecificTeacherCourseSeriesSearchCommand command);
}
