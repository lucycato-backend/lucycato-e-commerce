package org.lucycato.productcommandservice.application.port.out;

public interface TeacherQueryPort {
    Long getTeacherIdByCourseSeriesId(Long courseSeriesId);

    Long getTeacherIdByCourseId(Long courseId);

    Long getTeacherIdByLectureId(Long lectureId);

    Long getTeacherIdByTextEBookId(Long textEBookId);
}
