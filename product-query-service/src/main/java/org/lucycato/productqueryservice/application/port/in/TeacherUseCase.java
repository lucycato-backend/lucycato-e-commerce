package org.lucycato.productqueryservice.application.port.in;


import org.lucycato.productqueryservice.application.port.in.command.*;
import org.lucycato.productqueryservice.domain.Teacher;
import org.lucycato.productqueryservice.domain.TeacherCourseSeries;
import org.lucycato.productqueryservice.domain.TeacherDetail;
import org.lucycato.productqueryservice.domain.TeacherNews;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherUseCase {
    Flux<Teacher> getTeachers(TeacherSearchCommand command);

    Mono<TeacherDetail> getTeacherDetail(TeacherDetailSearchCommand command);

    Flux<TeacherCourseSeries> getTeacherCourseSeries(TeacherCourseSeriesSearchCommand command);

    Flux<TeacherCourseSeries> getTeacherCourseSeries(SpecificTeacherCourseSeriesSearchCommand command);

    Flux<TeacherNews> getTeacherNews(TeacherNewsSearchCommand command);

    Flux<TeacherNews> getTeacherNews(SpecificTeacherNewsSearchCommand command);
}
