package org.lucycato.boardcommandservice.application.port.out;

import org.lucycato.boardcommandservice.application.port.out.result.*;

public interface TeacherPort {

    TeacherNoticeResult createTeacherNotice(
            Long teacherId, String title, String content, String type);

    CourseReviewResult createCourseReview(
            Long id, Long teacherId, Long lectureId, String title, String content, int score);

    QnAResult createQna(
            Long id, Long teacherId, Long lectureId, String title, String content);


    ExamStoryResult createExamStory(Long id, Long teacherId, String title, String content, String type);

    QnAResult createAnswer(Long id, Long qnaId, String answer);
}
