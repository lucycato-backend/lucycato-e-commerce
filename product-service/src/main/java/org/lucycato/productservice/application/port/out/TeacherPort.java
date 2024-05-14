package org.lucycato.productservice.application.port.out;

import org.lucycato.productservice.application.port.out.result.TeacherResult;
import org.lucycato.productservice.domain.enums.TeachingGenre;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TeacherPort {
    Mono<TeacherResult> registerTeacher(
            String teacherName,
            String teacherSlogan,
            String profileDescription,
            TeachingGenre teachingGenre
    );

    Mono<TeacherResult> modifyTeacher(
            Long teacherId,
            List<String> teacherImageUrls,
            String curriculumImageUrl,
            String videoImageUrl
    );
}