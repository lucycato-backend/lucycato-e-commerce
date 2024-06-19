package org.lucycato.boardqueryservice.application.port.in;

import org.lucycato.boardqueryservice.application.port.in.command.GetTeacherNoticeCommand;
import org.lucycato.boardqueryservice.domain.TeacherNotice;

public interface TeacherBoardUseCase {

    TeacherNotice getTeacherNotice(GetTeacherNoticeCommand command);
}
