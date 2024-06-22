package org.lucycato.boardqueryservice.adpapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardqueryservice.application.port.out.TeacherRedisPort;
import org.lucycato.boardqueryservice.domain.RecentTeacherNotice;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeacherRedisAdapter implements TeacherRedisPort {
    @Override
    public Mono<RecentTeacherNotice> getRecentTeacherNotice(Long teacherId) {
        return null;
    }
}
