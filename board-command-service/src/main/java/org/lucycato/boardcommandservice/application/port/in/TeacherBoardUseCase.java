package org.lucycato.boardcommandservice.application.port.in;

import org.lucycato.boardcommandservice.application.port.in.command.*;
import org.lucycato.boardcommandservice.domain.*;

public interface TeacherBoardUseCase {

    TeacherNotice createTeacherNotice(CreateTeacherNoticeCommand command);

    CourseReview createCourseReview(CreateCourseReviewCommand command);

    QnA createQna(CreateQnaCommand command);

    ExamStory createExamStory(CreateExamStoryCommand command);

    QnA createAnswer(CreateAnswerCommand command);
}
