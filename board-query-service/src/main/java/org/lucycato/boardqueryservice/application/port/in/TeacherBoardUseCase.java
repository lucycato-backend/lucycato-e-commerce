package org.lucycato.boardqueryservice.application.port.in;

import org.lucycato.boardqueryservice.application.port.in.command.GetTeacherNoticeCommand;
import org.lucycato.boardqueryservice.domain.RecentTeacherNotice;
import org.lucycato.boardqueryservice.domain.TeacherNotice;
import reactor.core.publisher.Mono;

public interface TeacherBoardUseCase {

    TeacherNotice getTeacherNotice(GetTeacherNoticeCommand command);

    Mono<RecentTeacherNotice> getRecentTeacherNotice(GetTeacherNoticeCommand command);
}
