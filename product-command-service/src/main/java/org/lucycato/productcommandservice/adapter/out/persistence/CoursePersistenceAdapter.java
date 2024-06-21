package org.lucycato.productcommandservice.adapter.out.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.AsyncTaskProducer;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.kafka.AsyncTask;
import org.lucycato.common.kafka.TaskKey;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.CheckedRecentCourseOpenRedisEntity;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseJpaEntity;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseSeriesJpaEntity;
import org.lucycato.productcommandservice.adapter.out.persistence.repository.CourseJpaRepository;
import org.lucycato.productcommandservice.adapter.out.persistence.task.CourseUploadTask;
import org.lucycato.productcommandservice.application.port.out.CoursePort;
import org.lucycato.productcommandservice.application.port.out.result.CourseDetailResult;
import org.lucycato.productcommandservice.domain.enums.CourseGenre;
import org.lucycato.productcommandservice.domain.enums.CourseStatus;
import org.lucycato.productcommandservice.domain.enums.ProductBroadcastingCategory;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class CoursePersistenceAdapter implements CoursePort {
    private final String PRODUCT_SERVICE_RECENT_COURSE_UPLOAD_BY_TEACHER_ID_HASH_KEY = "product-service:courses:recent-upload:by-teacher-id";
    private final String PRODUCT_SERVICE_RECENT_COURSE_UPLOAD_BY_COURSE_ID_HASH_KEY = "product-service:courses:recent-upload:by-course-id";

    private final ObjectMapper objectMapper;

    private final CourseJpaRepository courseJpaRepository;

    private final RedisTemplate<String, String> redisTemplate;

    private final AsyncTaskProducer asyncTaskProducer;

    @Override
    public CourseDetailResult registerCourse(
            Long courseSeriesId,
            String courseTitle,
            String courseSubTitle,
            Integer coursePrice,
            String courseImageUrl,
            String courseDescription,
            CourseGenre courseGenre,
            SubjectCategory subjectCategory,
            CourseStatus courseStatus,
            LocalDateTime courseExpiredAt
    ) {
        return CourseDetailResult.from(courseJpaRepository.save(CourseJpaEntity.create(
                courseSeriesId,
                courseTitle,
                courseSubTitle,
                coursePrice,
                courseDescription,
                courseGenre,
                subjectCategory,
                courseStatus,
                courseExpiredAt
        )));
    }

    @Override
    public CourseDetailResult modifyCourse(
            Long courseId,
            Long courseSeriesId,
            String courseTitle,
            String courseSubTitle,
            Integer coursePrice,
            String courseImageUrl,
            String courseDescription,
            CourseGenre courseGenre,
            SubjectCategory subjectCategory,
            LocalDateTime courseExpiredAt
    ) {
        CourseJpaEntity courseJpaEntity = courseJpaRepository.findById(courseId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        CourseSeriesJpaEntity courseSeriesJpaEntity = CourseSeriesJpaEntity.builder()
                .id(courseId)
                .build();
        courseJpaEntity.setCourseSeriesJpaEntity(courseSeriesJpaEntity);
        courseJpaEntity.setCourseTitle(courseTitle);
        courseJpaEntity.setCourseSubTitle(courseSubTitle);
        courseJpaEntity.setCoursePrice(coursePrice);
        courseJpaEntity.setCourseImageUrl(courseImageUrl);
        courseJpaEntity.setCourseDescription(courseDescription);
        courseJpaEntity.setCourseGenre(courseGenre);
        courseJpaEntity.setSubjectCategory(subjectCategory);
        courseJpaEntity.setCourseExpiredAt(courseExpiredAt);

        return CourseDetailResult.from(courseJpaRepository.save(courseJpaEntity));
    }

    @Override
    public Boolean registerRecentCourseOpen(
            Long courseId,
            Long teacherId
    ) {
        CheckedRecentCourseOpenRedisEntity entity = new CheckedRecentCourseOpenRedisEntity(courseId, teacherId);
        try {
            String json = objectMapper.writeValueAsString(entity);
            redisTemplate.opsForHash().put(PRODUCT_SERVICE_RECENT_COURSE_UPLOAD_BY_TEACHER_ID_HASH_KEY, teacherId, json);
            redisTemplate.opsForHash().put(PRODUCT_SERVICE_RECENT_COURSE_UPLOAD_BY_COURSE_ID_HASH_KEY, courseId, json);
            return true;
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.JSON_PARSING, e);
        }
    }

    @Override
    public Boolean getRecentCourseOpen(Long courseId) {
        return Optional.ofNullable(redisTemplate.opsForHash().get(PRODUCT_SERVICE_RECENT_COURSE_UPLOAD_BY_COURSE_ID_HASH_KEY, courseId))
                .isPresent();
    }

    @Override
    public void sendRecentCourseOpen(
            Long courseId,
            Long teacherId,
            ProductBroadcastingCategory productBroadcastingCategory
    ) {
        String topic = "product-service";
        TaskKey taskKey = new TaskKey(topic, UUID.randomUUID().toString());
        CourseUploadTask courseUploadTask = new CourseUploadTask(productBroadcastingCategory, courseId, teacherId);
        AsyncTask<?> asyncTask = AsyncTask.CREATE(taskKey, courseUploadTask);
        try {
            asyncTaskProducer.sendAsyncTask(asyncTask);
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.KAFKA_SEND_FAIL);
        }
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseJpaRepository.deleteById(courseId);
    }
}
