package org.lucycato.productcommandservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseSeriesJpaEntity;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.TeacherJpaEntity;
import org.lucycato.productcommandservice.adapter.out.persistence.repository.CourseSeriesJpaRepository;
import org.lucycato.productcommandservice.application.port.out.CourseSeriesPort;
import org.lucycato.productcommandservice.application.port.out.result.CourseSeriesDetailResult;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class CourseSeriesPersistenceAdapter implements CourseSeriesPort {
    private final CourseSeriesJpaRepository courseSeriesJpaRepository;

    @Override
    public CourseSeriesDetailResult registerCourseSeries(
            Long teacherId,
            String courseSeriesImageUrl,
            String courseSeriesTitle,
            String courseSeriesDescription,
            List<String> courseSeriesExplainImageUrls,
            SubjectCategory subjectCategory,
            CourseSeriesCategory courseSeriesCategory,
            CourseSeriesStatus courseSeriesStatus
    ) {
        return CourseSeriesDetailResult.from(courseSeriesJpaRepository.save(CourseSeriesJpaEntity.create(
                teacherId,
                courseSeriesImageUrl,
                courseSeriesTitle,
                courseSeriesDescription,
                courseSeriesExplainImageUrls,
                subjectCategory,
                courseSeriesCategory,
                courseSeriesStatus
        )));
    }

    @Override
    public CourseSeriesDetailResult modifyCourseSeries(
            Long courseSeriesId,
            Long teacherId,
            String courseSeriesImageUrl,
            String courseSeriesTitle,
            String courseSeriesDescription,
            List<String> courseSeriesExplainImageUrls,
            SubjectCategory subjectCategory,
            CourseSeriesCategory courseSeriesCategory
    ) {
        CourseSeriesJpaEntity courseSeriesJpaEntity = courseSeriesJpaRepository.findById(courseSeriesId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        TeacherJpaEntity teacherJpaEntity = TeacherJpaEntity.builder()
                .id(teacherId)
                .build();
        courseSeriesJpaEntity.setTeacherJpaEntity(teacherJpaEntity);
        courseSeriesJpaEntity.setCourseSeriesImageUrl(courseSeriesImageUrl);
        courseSeriesJpaEntity.setCourseSeriesTitle(courseSeriesTitle);
        courseSeriesJpaEntity.setCourseSeriesDescription(courseSeriesDescription);
        courseSeriesJpaEntity.setCourseSeriesExplainImageUrlsJson(courseSeriesExplainImageUrls);
        courseSeriesJpaEntity.setSubjectCategory(subjectCategory);
        courseSeriesJpaEntity.setCourseSeriesCategory(courseSeriesCategory);

        return CourseSeriesDetailResult.from(courseSeriesJpaRepository.save(courseSeriesJpaEntity));
    }

    @Override
    public void deleteCourseSeries(Long courseSeries) {
        courseSeriesJpaRepository.deleteById(courseSeries);
    }
}
