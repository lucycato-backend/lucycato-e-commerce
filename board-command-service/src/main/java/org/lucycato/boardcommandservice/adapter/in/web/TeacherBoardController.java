package org.lucycato.boardcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardcommandservice.adapter.in.web.requset.*;
import org.lucycato.boardcommandservice.application.port.in.TeacherBoardUseCase;
import org.lucycato.boardcommandservice.application.port.in.command.*;
import org.lucycato.boardcommandservice.domain.CUDReturnId;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping
@RequiredArgsConstructor
public class TeacherBoardController {

    private final TeacherBoardUseCase teacherBoardUseCase;

    @PostMapping("api/app/board/v1/teachers/notices")
    public CUDReturnId createTeacherNotice(
            @RequestBody TeacherNoticeRequest request
    ) {
        CreateTeacherNoticeCommand command = new CreateTeacherNoticeCommand(
                1L,
                request.getTitle(),
                request.getContent(),
                request.getType()
        );

        return teacherBoardUseCase.createTeacherNotice(command);
    }

    @PostMapping("api/app/board/v1/course-review")
    public CUDReturnId createCourseReview(
            @RequestBody CourseReviewRequest request
    ) {
        CreateCourseReviewCommand command = new CreateCourseReviewCommand(
                2L,
                request.getTeacherId(),
                request.getLectureId(),
                request.getTitle(),
                request.getContent(),
                request.getScore()
        );
        return teacherBoardUseCase.createCourseReview(command);
    }

    @PostMapping("api/app/board/v1/teachers/qna")
    public CUDReturnId createQna(
            @RequestBody QuestionRequest request
    ) {
        CreateQnaCommand command = new CreateQnaCommand(
                3L,
                request.getTeacherId(),
                request.getLectureId(),
                request.getTitle(),
                request.getContent()
        );
        return teacherBoardUseCase.createQna(command);
    }

    @PatchMapping("api/app/board/v1/teachers/qna/{qnaId}")
    public CUDReturnId createAnswer(
            @PathVariable Long qnaId,
            @RequestBody AnswerRequest request
    ) {
        CreateAnswerCommand command = new CreateAnswerCommand(
                5L,
                qnaId,
                request.getAnswer()
        );
        return teacherBoardUseCase.createAnswer(command);
    }

    @PostMapping("api/app/board/v1/exam-story")
    public CUDReturnId createExamStory(
            @RequestBody ExamStoryRequest request
    ) {
        CreateExamStoryCommand command = new CreateExamStoryCommand(
                4L,
                request.getTeacherId(),
                request.getTitle(),
                request.getContent(),
                request.getType()
        );
        return teacherBoardUseCase.createExamStory(command);
    }

    @DeleteMapping("api/app/board/v1/teacher/notices/{noticeId}")
    public CUDReturnId deleteTeacherNotice(
            @PathVariable Long noticeId
    ) {
        

    }
}
