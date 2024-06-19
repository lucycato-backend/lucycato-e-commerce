package org.lucycato.boardcommandservice.application.port.out;

import org.lucycato.boardcommandservice.application.port.out.result.CUDReturnIdResult;

public interface TeacherPort {

    CUDReturnIdResult createTeacherNotice(
            Long teacherId, String title, String content, String type);

    CUDReturnIdResult createCourseReview(
            Long id, Long teacherId, Long lectureId, String title, String content, int score);

    CUDReturnIdResult createQna(
            Long id, Long teacherId, Long lectureId, String title, String content);


    CUDReturnIdResult createExamStory(Long id, Long teacherId, String title, String content, String type);

    CUDReturnIdResult createAnswer(Long id, Long qnaId, String answer);
}
