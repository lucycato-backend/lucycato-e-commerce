package org.lucycato.boardcommandservice.adapter.out.persistence;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.lucycato.boardcommandservice.adapter.out.persistence.entity.CourseReviewJpaEntity;
import org.lucycato.boardcommandservice.adapter.out.persistence.entity.ExamStoryJpaEntity;
import org.lucycato.boardcommandservice.adapter.out.persistence.entity.TeacherNoticeJpaEntity;
import org.lucycato.boardcommandservice.adapter.out.persistence.entity.TeacherQnAJpaEntity;
import org.lucycato.boardcommandservice.adapter.out.persistence.repository.CourseReviewJpaRepository;
import org.lucycato.boardcommandservice.adapter.out.persistence.repository.ExamStoryJpaRepository;
import org.lucycato.boardcommandservice.adapter.out.persistence.repository.TeacherNoticeJpaRepository;
import org.lucycato.boardcommandservice.adapter.out.persistence.repository.TeacherQnAJpaRepository;
import org.lucycato.boardcommandservice.application.port.out.TeacherPort;
import org.lucycato.boardcommandservice.application.port.out.result.*;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;


@PersistenceAdapter
@RequiredArgsConstructor
public class TeacherNoticePersistenceAdapter implements TeacherPort {

    private final TeacherNoticeJpaRepository noticeJpaRepository;
    private final CourseReviewJpaRepository courseReviewJpaRepository;
    private final TeacherQnAJpaRepository teacherQnAJpaRepository;
    private final ExamStoryJpaRepository examStoryJpaRepository;
    @Override
    public TeacherNoticeResult createTeacherNotice(Long teacherId, String title, String content, String type) {
        TeacherNoticeJpaEntity teacherNoticeJpaEntity = new TeacherNoticeJpaEntity(
                teacherId, title, content, type
        );
        TeacherNoticeJpaEntity savedEntity = noticeJpaRepository.save(teacherNoticeJpaEntity);
        return TeacherNoticeResult.from(savedEntity);
    }

    @Override
    public CourseReviewResult createCourseReview(Long id, Long teacherId, Long lectureId, String title, String content, int score) {
        CourseReviewJpaEntity courseReviewJpaEntity = new CourseReviewJpaEntity(
                id, teacherId, lectureId, title, content, score
        );
        CourseReviewJpaEntity savedEntity = courseReviewJpaRepository.save(courseReviewJpaEntity);
        return CourseReviewResult.from(savedEntity);
    }

    @Override
    public QnAResult createQna(Long id, Long teacherId, Long lectureId, String title, String content) {
        TeacherQnAJpaEntity teacherQnaJpaEntity = new TeacherQnAJpaEntity(
                id, teacherId, lectureId, title, content);
        TeacherQnAJpaEntity savedEntity = teacherQnAJpaRepository.save(teacherQnaJpaEntity);
        return QnAResult.from(savedEntity);
    }

    @Override
    public ExamStoryResult createExamStory(Long id, Long teacherId, String title, String content, String type) {
        ExamStoryJpaEntity examStoryJpaEntity = new ExamStoryJpaEntity(id, teacherId, title, content, type);
        ExamStoryJpaEntity savedEntity = examStoryJpaRepository.save(examStoryJpaEntity);
        return ExamStoryResult.from(savedEntity);

    }

    @Override
    public QnAResult createAnswer(Long id, Long qnaId, String answer) {
        TeacherQnAJpaEntity teacherQnAJpaEntity = teacherQnAJpaRepository.findById(qnaId).orElseThrow(
                () -> new EntityNotFoundException("Entity not found with ID: " + qnaId));
        teacherQnAJpaEntity.setTeacherId(id);
        teacherQnAJpaEntity.setAnswer(answer);
        return QnAResult.from(teacherQnAJpaEntity);
    }
}
