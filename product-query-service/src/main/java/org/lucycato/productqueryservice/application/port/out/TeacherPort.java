package org.lucycato.productqueryservice.application.port.out;

import org.lucycato.productqueryservice.application.port.out.result.TeacherDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.TeacherResult;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherPort {

    Flux<Long> getTeacherIdsByTeachingGenre(TeachingGenre teachingGenre);

    Flux<TeacherResult> getTeacherListByTeachingGenre(TeachingGenre teachingGenre);

    Mono<TeacherResult> getSimpleTeacher(Long teacherId);

    Mono<TeacherDetailResult> getTeacher(Long teacherId);
}
