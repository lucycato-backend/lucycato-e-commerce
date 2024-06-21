package org.lucycato.boardqueryservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardqueryservice.application.port.in.TeacherBoardUseCase;
import org.lucycato.boardqueryservice.application.port.in.command.GetTeacherNoticeCommand;
import org.lucycato.boardqueryservice.domain.RecentTeacherNotice;
import org.lucycato.boardqueryservice.domain.TeacherNotice;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TeacherBoardService implements TeacherBoardUseCase {
    @Override
    public TeacherNotice getTeacherNotice(GetTeacherNoticeCommand command) {
        return null;
    }

    @Override
    public Mono<RecentTeacherNotice> getRecentTeacherNotice(GetTeacherNoticeCommand command) {
        return null;
    }
}
