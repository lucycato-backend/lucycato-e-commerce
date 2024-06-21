package org.lucycato.productcommandservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseJpaEntity;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.LectureJpaEntity;
import org.lucycato.productcommandservice.adapter.out.persistence.repository.LectureJpaRepository;
import org.lucycato.productcommandservice.application.port.out.LecturePort;
import org.lucycato.productcommandservice.application.port.out.result.LectureDetailResult;
import org.lucycato.productcommandservice.domain.enums.LectureCategory;
import org.lucycato.productcommandservice.domain.enums.LectureStatus;

@PersistenceAdapter
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements LecturePort {
    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public LectureDetailResult registerLecture(
            Long courseId,
            String lectureTitle,
            String lectureDescription,
            String lectureThumbnailImageUrl,
            String lectureVideoUrl,
            LectureCategory lectureCategory,
            LectureStatus lectureStatus
    ) {
        return LectureDetailResult.from(lectureJpaRepository.save(LectureJpaEntity.create(
                courseId,
                lectureTitle,
                lectureDescription,
                lectureThumbnailImageUrl,
                lectureVideoUrl,
                lectureCategory,
                lectureStatus
        )));
    }

    @Override
    public LectureDetailResult modifyLecture(
            Long lectureId,
            Long courseId,
            String lectureTitle,
            String lectureDescription,
            String lectureThumbnailImageUrl,
            String lectureVideoUrl,
            LectureCategory lectureCategory
    ) {
        LectureJpaEntity lectureJpaEntity = lectureJpaRepository.findById(lectureId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        CourseJpaEntity courseJpaEntity = CourseJpaEntity.builder()
                .id(courseId)
                .build();
        lectureJpaEntity.setCourseJpaEntity(courseJpaEntity);
        lectureJpaEntity.setLectureDescription(lectureDescription);
        lectureJpaEntity.setLectureThumbnailImageUrl(lectureThumbnailImageUrl);
        lectureJpaEntity.setLectureVideoUrl(lectureVideoUrl);
        lectureJpaEntity.setLectureCategory(lectureCategory);

        return LectureDetailResult.from(lectureJpaRepository.save(lectureJpaEntity));
    }

    @Override
    public void deleteLecture(Long lectureId) {
        lectureJpaRepository.deleteById(lectureId);
    }
}
