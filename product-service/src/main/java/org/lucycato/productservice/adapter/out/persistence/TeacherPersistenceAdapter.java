package org.lucycato.productservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productservice.adapter.out.persistence.entity.TeacherR2dbcEntity;
import org.lucycato.productservice.adapter.out.persistence.repository.TeacherReactiveRepository;
import org.lucycato.productservice.application.port.out.TeacherPort;
import org.lucycato.productservice.application.port.out.result.TeacherResult;
import org.lucycato.productservice.domain.enums.TeachingGenre;
import reactor.core.publisher.Mono;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeacherPersistenceAdapter implements TeacherPort {
    private final TeacherReactiveRepository teacherReactiveRepository;

    @Override
    public Mono<TeacherResult> registerTeacher(
            String teacherName,
            String teacherSlogan,
            String profileDescription,
            TeachingGenre teachingGenre
    ) {
        return teacherReactiveRepository.save(new TeacherR2dbcEntity(
                        teacherName,
                        teacherSlogan,
                        profileDescription,
                        teachingGenre
                ))
                .map(TeacherResult::from);
    }

    @Override
    public Mono<TeacherResult> modifyTeacher(Long teacherId, List<String> teacherImageUrls, String curriculumImageUrl, String videoImageUrl) {
        return teacherReactiveRepository.findById(teacherId)
                .flatMap(entity -> {
                    entity.setImageUrls(teacherImageUrls);
                    entity.setCurriculumImageUrl(curriculumImageUrl);
                    entity.setCurriculumVideoUrl(videoImageUrl);
                    return teacherReactiveRepository.save(entity);
                })
                .map(TeacherResult::from);
    }
}
