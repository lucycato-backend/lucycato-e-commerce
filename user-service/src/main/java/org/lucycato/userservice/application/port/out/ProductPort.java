package org.lucycato.userservice.application.port.out;

import java.util.List;

public interface ProductPort {
    List<Long> getAppUserIdsByLectureIds(List<Long> lectureIds);

    List<Long> getAppUserIdsByTeacherIds(List<Long> teacherIds);
}
