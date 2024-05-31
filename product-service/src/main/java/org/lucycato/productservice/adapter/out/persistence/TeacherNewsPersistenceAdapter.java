package org.lucycato.productservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productservice.adapter.out.persistence.entity.TeacherNewsR2dbcEntity;
import org.lucycato.productservice.adapter.out.persistence.repository.TeacherNewsReactiveRepository;
import org.lucycato.productservice.application.port.out.TeacherNewsPort;
import org.lucycato.productservice.application.port.out.result.TeacherNewsResult;
import org.lucycato.productservice.domain.TeacherNews;
import org.lucycato.productservice.domain.enums.TeacherNewStatus;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeacherNewsPersistenceAdapter implements TeacherNewsPort {
    private final TeacherNewsReactiveRepository teacherNewsReactiveRepository;

    @Override
    public Mono<TeacherNewsResult> registerTeacherNews(Long teacherId, String title, String content, TeacherNewStatus teacherNewStatus) {
        return teacherNewsReactiveRepository.save(new TeacherNewsR2dbcEntity(teacherId, title, content, teacherNewStatus)).map(TeacherNewsResult::from);
    }
}
