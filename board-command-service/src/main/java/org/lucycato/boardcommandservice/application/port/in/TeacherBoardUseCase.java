package org.lucycato.boardcommandservice.application.port.in;

import org.lucycato.boardcommandservice.application.port.in.command.*;
import org.lucycato.boardcommandservice.domain.CUDReturnId;

public interface TeacherBoardUseCase {

    CUDReturnId createTeacherNotice(CreateTeacherNoticeCommand command);

    CUDReturnId createCourseReview(CreateCourseReviewCommand command);

    CUDReturnId createQna(CreateQnaCommand command);

    CUDReturnId createExamStory(CreateExamStoryCommand command);

    CUDReturnId createAnswer(CreateAnswerCommand command);
}
