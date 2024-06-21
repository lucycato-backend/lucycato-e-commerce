package org.lucycato.productcommandservice.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.*;
import org.lucycato.productcommandservice.adapter.out.persistence.repository.TeacherJpaRepository;
import org.lucycato.productcommandservice.application.port.out.TeacherPort;
import org.lucycato.productcommandservice.application.port.out.TeacherQueryPort;
import org.lucycato.productcommandservice.application.port.out.result.TeacherDetailResult;
import org.lucycato.productcommandservice.domain.enums.TeacherStatus;
import org.lucycato.productcommandservice.domain.enums.TeachingGenre;

@PersistenceAdapter
public class TeacherPersistenceAdapter implements TeacherPort, TeacherQueryPort {
    private final TeacherJpaRepository teacherJpaRepository;

    private final JPAQueryFactory queryFactory;

    public TeacherPersistenceAdapter(TeacherJpaRepository teacherJpaRepository, EntityManager em) {
        this.teacherJpaRepository = teacherJpaRepository;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public TeacherDetailResult registerTeacher(
            Integer teacherRank,
            String teacherName,
            String teacherSlogan,
            String teacherProfileDescription,
            String teacherImageUrl,
            String teacherCurriculumImageUrl,
            String teacherCurriculumVideoUrl,
            TeachingGenre teachingGenre,
            TeacherStatus teacherStatus
    ) {
        return TeacherDetailResult.from(teacherJpaRepository.save(TeacherJpaEntity.create(
                teacherRank,
                teacherName,
                teacherSlogan,
                teacherProfileDescription,
                teacherImageUrl,
                teacherCurriculumImageUrl,
                teacherCurriculumVideoUrl,
                teachingGenre,
                teacherStatus
        )));
    }

    @Override
    public TeacherDetailResult modifyTeacher(
            Long teacherId,
            Integer teacherRank,
            String teacherName,
            String teacherSlogan,
            String teacherProfileDescription,
            String teacherImageUrl,
            String teacherCurriculumImageUrl,
            String teacherCurriculumVideoUrl,
            TeachingGenre teachingGenre
    ) {
        TeacherJpaEntity teacherJpaEntity = teacherJpaRepository.findById(teacherId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        teacherJpaEntity.setTeacherRank(teacherRank);
        teacherJpaEntity.setTeacherName(teacherName);
        teacherJpaEntity.setTeacherSlogan(teacherSlogan);
        teacherJpaEntity.setTeacherProfileDescription(teacherProfileDescription);
        teacherJpaEntity.setTeacherImageUrl(teacherImageUrl);
        teacherJpaEntity.setTeacherCurriculumImageUrl(teacherCurriculumImageUrl);
        teacherJpaEntity.setTeacherCurriculumVideoUrl(teacherCurriculumVideoUrl);
        teacherJpaEntity.setTeachingGenre(teachingGenre);

        return TeacherDetailResult.from(teacherJpaRepository.save(teacherJpaEntity));
    }

    @Override
    public void removeTeacher(Long teacherId) {
        teacherJpaRepository.deleteById(teacherId);
    }

    @Override
    public Long getTeacherIdByCourseSeriesId(Long courseSeriesId) {
        QCourseSeriesJpaEntity courseSeriesJpaEntity = QCourseSeriesJpaEntity.courseSeriesJpaEntity;

        return queryFactory
                .select(courseSeriesJpaEntity.teacherJpaEntity.id)
                .from(courseSeriesJpaEntity)
                .where(courseSeriesJpaEntity.id.eq(courseSeriesId))
                .fetchOne();
    }

    @Override
    public Long getTeacherIdByCourseId(Long courseId) {
        QCourseJpaEntity courseJpaEntity = QCourseJpaEntity.courseJpaEntity;
        QCourseSeriesJpaEntity courseSeriesJpaEntity = QCourseSeriesJpaEntity.courseSeriesJpaEntity;

        return queryFactory
                .select(courseSeriesJpaEntity.teacherJpaEntity.id)
                .from(courseJpaEntity)
                .innerJoin(courseJpaEntity.courseSeriesJpaEntity, courseSeriesJpaEntity)
                .where(courseJpaEntity.id.eq(courseId))
                .fetchOne();
    }

    @Override
    public Long getTeacherIdByLectureId(Long lectureId) {
        QLectureJpaEntity lectureJpaEntity = QLectureJpaEntity.lectureJpaEntity;
        QCourseJpaEntity courseJpaEntity = QCourseJpaEntity.courseJpaEntity;
        QCourseSeriesJpaEntity courseSeriesJpaEntity = QCourseSeriesJpaEntity.courseSeriesJpaEntity;

        return queryFactory
                .select(courseSeriesJpaEntity.teacherJpaEntity.id)
                .from(lectureJpaEntity)
                .innerJoin(lectureJpaEntity.courseJpaEntity, courseJpaEntity)
                .innerJoin(courseJpaEntity.courseSeriesJpaEntity, courseSeriesJpaEntity)
                .where(lectureJpaEntity.id.eq(lectureId))
                .fetchOne();
    }

    @Override
    public Long getTeacherIdByTextEBookId(Long textEBookId) {
        QTextEBookJpaEntity textEBookJpaEntity = QTextEBookJpaEntity.textEBookJpaEntity;
        QCourseJpaEntity courseJpaEntity = QCourseJpaEntity.courseJpaEntity;
        QCourseSeriesJpaEntity courseSeriesJpaEntity = QCourseSeriesJpaEntity.courseSeriesJpaEntity;

        return queryFactory
                .select(courseSeriesJpaEntity.teacherJpaEntity.id)
                .from(textEBookJpaEntity)
                .innerJoin(textEBookJpaEntity.courseJpaEntity, courseJpaEntity)
                .innerJoin(courseJpaEntity.courseSeriesJpaEntity, courseSeriesJpaEntity)
                .where(textEBookJpaEntity.id.eq(textEBookId))
                .fetchOne();
    }
}
