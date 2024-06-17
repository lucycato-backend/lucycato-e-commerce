package org.lucycato.productqueryservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productqueryservice.application.port.out.TeacherPort;
import org.lucycato.productqueryservice.application.port.out.result.TeacherDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.TeacherResult;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeacherPersistenceAdapter implements TeacherPort {

    @Override
    public Flux<Long> getTeacherIdsByTeachingGenre(TeachingGenre teachingGenre) {
        return null;
    }

    @Override
    public Flux<TeacherResult> getTeacherListByTeachingGenre(TeachingGenre teachingGenre) {
        return null;
    }

    @Override
    public Mono<TeacherResult> getSimpleTeacher(Long teacherId) {
        return null;
    }

    @Override
    public Mono<TeacherDetailResult> getTeacher(Long teacherId) {
        return null;
    }
}
