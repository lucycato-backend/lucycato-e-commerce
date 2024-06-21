package org.lucycato.boardcommandservice.application.port.out;

import java.util.List;

public interface TeacherRedisPort {

    void addTeacherNoticeRedis(Long teacherId, Long noticeId);

    List<Long> getTeacherNotices(Long teacherId);
}
