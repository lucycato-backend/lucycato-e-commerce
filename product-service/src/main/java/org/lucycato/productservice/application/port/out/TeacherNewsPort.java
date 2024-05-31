package org.lucycato.productservice.application.port.out;

import org.lucycato.productservice.application.port.out.result.TeacherNewsResult;
import org.lucycato.productservice.domain.enums.TeacherNewStatus;
import reactor.core.publisher.Mono;

public interface TeacherNewsPort {

    Mono<TeacherNewsResult> registerTeacherNews(
            Long teacherId,
            String title,
            String content,
            TeacherNewStatus teacherNewStatus
    );

}
