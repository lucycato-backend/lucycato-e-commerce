package org.lucycato.boardcommandservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardcommandservice.application.port.in.TeacherBoardUseCase;
import org.lucycato.boardcommandservice.application.port.in.command.*;
import org.lucycato.boardcommandservice.application.port.out.TeacherPort;
import org.lucycato.boardcommandservice.application.port.out.result.CUDReturnIdResult;
import org.lucycato.boardcommandservice.domain.CUDReturnId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherBoardService implements TeacherBoardUseCase {

    private final TeacherPort teacherPort;

    @Override
    public CUDReturnId createTeacherNotice(CreateTeacherNoticeCommand command) {
        CUDReturnIdResult cudReturnIdResult = teacherPort.createTeacherNotice(
                command.getTeacherId(),
                command.getTitle(),
                command.getContent(),
                command.getType()
        );
        return CUDReturnId.from(cudReturnIdResult);
    }

    @Override
    public CUDReturnId createCourseReview(CreateCourseReviewCommand command) {
        CUDReturnIdResult cudReturnIdResult = teacherPort.createCourseReview(
                command.getId(),
                command.getTeacherId(),
                command.getLectureId(),
                command.getTitle(),
                command.getContent(),
                command.getScore()
        );
        return CUDReturnId.from(cudReturnIdResult);
    }

    @Override
    public CUDReturnId createQna(CreateQnaCommand command) {
        CUDReturnIdResult cudReturnIdResult = teacherPort.createQna(
                command.getId(),
                command.getTeacherId(),
                command.getLectureId(),
                command.getTitle(),
                command.getContent()
        );
        return CUDReturnId.from(cudReturnIdResult);
    }

    @Override
    public CUDReturnId createExamStory(CreateExamStoryCommand command) {
        CUDReturnIdResult cudReturnIdResult = teacherPort.createExamStory(
                command.getId(),
                command.getTeacherId(),
                command.getTitle(),
                command.getContent(),
                command.getType()
        );
        return CUDReturnId.from(cudReturnIdResult);
    }

    @Override
    public CUDReturnId createAnswer(CreateAnswerCommand command) {
        CUDReturnIdResult cudReturnIdResult = teacherPort.createAnswer(
                command.getId(),
                command.getQnaId(),
                command.getAnswer()
        );
        return CUDReturnId.from(cudReturnIdResult);
    }

}
