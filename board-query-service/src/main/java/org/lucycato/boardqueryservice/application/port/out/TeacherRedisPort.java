package org.lucycato.boardqueryservice.application.port.out;

import org.lucycato.boardqueryservice.domain.RecentTeacherNotice;
import reactor.core.publisher.Mono;

public interface TeacherRedisPort {

    Mono<RecentTeacherNotice> getRecentTeacherNotice(Long teacherId);
}
