package org.lucycato.boardcommandservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardcommandservice.application.port.in.TeacherBoardUseCase;
import org.lucycato.boardcommandservice.application.port.in.command.*;
import org.lucycato.boardcommandservice.application.port.out.TeacherPort;
import org.lucycato.boardcommandservice.application.port.out.TeacherRedisPort;
import org.lucycato.boardcommandservice.application.port.out.result.*;
import org.lucycato.boardcommandservice.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherBoardService implements TeacherBoardUseCase {

    private final TeacherPort teacherPort;
    private final TeacherRedisPort teacherRedisPort;

    @Override
    public TeacherNotice createTeacherNotice(CreateTeacherNoticeCommand command) {
        TeacherNoticeResult teacherNoticeResult = teacherPort.createTeacherNotice(
                command.getTeacherId(),
                command.getTitle(),
                command.getContent(),
                command.getType()
        );
        teacherRedisPort.addTeacherNoticeRedis(teacherNoticeResult.getTeacherId(), teacherNoticeResult.getId());
        return TeacherNotice.from(teacherNoticeResult);
    }

    @Override
    public CourseReview createCourseReview(CreateCourseReviewCommand command) {
        CourseReviewResult courseReviewResult = teacherPort.createCourseReview(
                command.getId(),
                command.getTeacherId(),
                command.getLectureId(),
                command.getTitle(),
                command.getContent(),
                command.getScore()
        );
        return CourseReview.from(courseReviewResult);
    }

    @Override
    public QnA createQna(CreateQnaCommand command) {
        QnAResult qnAResult = teacherPort.createQna(
                command.getId(),
                command.getTeacherId(),
                command.getLectureId(),
                command.getTitle(),
                command.getContent()
        );
        return QnA.from(qnAResult);
    }

    @Override
    public ExamStory createExamStory(CreateExamStoryCommand command) {
        ExamStoryResult examStoryResult = teacherPort.createExamStory(
                command.getId(),
                command.getTeacherId(),
                command.getTitle(),
                command.getContent(),
                command.getType()
        );
        return ExamStory.from(examStoryResult);
    }

    @Override
    public QnA createAnswer(CreateAnswerCommand command) {
        QnAResult qnAResult = teacherPort.createAnswer(
                command.getId(),
                command.getQnaId(),
                command.getAnswer()
        );
        return QnA.from(qnAResult);
    }

}
