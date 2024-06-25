package org.lucycato.productqueryservice.application.port.out;

import org.lucycato.productqueryservice.application.port.out.result.TeacherDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.TeacherResult;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TeacherPort {

    Flux<Long> getTeacherIds();

    Flux<Long> getTeacherIds(TeachingGenre teachingGenre);

    Flux<TeacherResult> getTeacherListByTeacherIds(List<Long> teacherIds);

    Flux<TeacherResult> getTeacherList();

    Flux<TeacherResult> getTeacherList(TeachingGenre teachingGenre);

    Flux<TeacherResult> getTeacherList(TeachingGenre teachingGenre, Integer page, Integer size);

    Mono<TeacherResult> getSimpleTeacherByTeacherId(Long teacherId);

    Mono<TeacherResult> getSimpleTeacherByCourseSeriesId(Long courseSeriesId);

    Mono<TeacherDetailResult> getTeacherByTeacherId(Long teacherId);
}
