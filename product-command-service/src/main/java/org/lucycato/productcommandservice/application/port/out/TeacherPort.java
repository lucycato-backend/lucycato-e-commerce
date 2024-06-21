package org.lucycato.productcommandservice.application.port.out;

import org.lucycato.productcommandservice.application.port.out.result.TeacherDetailResult;
import org.lucycato.productcommandservice.domain.enums.TeacherStatus;
import org.lucycato.productcommandservice.domain.enums.TeachingGenre;

public interface TeacherPort {

    TeacherDetailResult registerTeacher(
            Integer teacherRank,
            String teacherName,
            String teacherSlogan,
            String teacherProfileDescription,
            String teacherImageUrl,
            String teacherCurriculumImageUrl,
            String teacherCurriculumVideoUrl,
            TeachingGenre teachingGenre,
            TeacherStatus teacherStatus
    );

    TeacherDetailResult modifyTeacher(
            Long teacherId,
            Integer teacherRank,
            String teacherName,
            String teacherSlogan,
            String teacherProfileDescription,
            String teacherImageUrl,
            String teacherCurriculumImageUrl,
            String teacherCurriculumVideoUrl,
            TeachingGenre teachingGenre
    );

    void removeTeacher(Long teacherId);
}
