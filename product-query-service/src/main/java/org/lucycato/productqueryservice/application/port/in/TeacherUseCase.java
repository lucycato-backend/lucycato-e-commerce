package org.lucycato.productqueryservice.application.port.in;


import org.lucycato.productqueryservice.application.port.in.command.TeacherDetailSearchCommand;
import org.lucycato.productqueryservice.application.port.in.command.TeacherSearchCommand;
import org.lucycato.productqueryservice.domain.Teacher;
import org.lucycato.productqueryservice.domain.TeacherDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TeacherUseCase {
    Flux<Teacher> getTeachers(TeacherSearchCommand command);

    Flux<TeacherCourseSeries> getTeacherCourseSeries(TeacherCourseSeriesSearchCommand command);

    Flux<TeacherCourseSeries> getTeacherCourseSeries(SpecificTeacherCourseSeriesSearchCommand command);

    Mono<TeacherDetail> getTeacher(TeacherDetailSearchCommand command);
}
