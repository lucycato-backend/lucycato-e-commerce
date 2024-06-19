package org.lucycato.productqueryservice.adapter.out.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productqueryservice.application.port.out.CoursePort;
import org.lucycato.productqueryservice.application.port.out.result.CheckedRecentCourseOpenResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseCountResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseResult;
import org.lucycato.productqueryservice.domain.enums.CourseGenre;
import org.lucycato.productqueryservice.domain.enums.CourseStatus;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class CoursePersistenceAdapter implements CoursePort {

    private final String PRODUCT_SERVICE_RECENT_COURSE_UPLOAD_BY_TEACHER_ID_HASH_KEY = "product-service:courses:recent-upload:by-teacher-id";

    private final ObjectMapper objectMapper;

    private final DatabaseClient databaseClient;

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    @Override
    public Mono<CourseResult> getSimpleCourse(Long courseId) {
        String sql = """
                SELECT c.id as courseId,
                    c.teacher_id as teacherId,
                    c.course_title as courseTitle,
                    c.course_sub_title as courseSubTitle,
                    c.course_price as coursePrice,
                    c.course_image_url as courseImageUrl,
                    c.course_genre as courseGenre,
                    c.subject_category as subjectCategory,
                    c.course_status as courseStatus,
                    c.course_expired_at as courseExpiredAt,
                    c.course_created_at as courseCreatedAt
                FROM courses c
                WHERE c.id = :courseId;
                """;

        return databaseClient.sql(sql)
                .bind("courseId", courseId)
                .fetch()
                .one()
                .map(row -> new CourseResult(
                        (Long) row.get("courseId"),
                        (Long) row.get("teacherId"),
                        (String) row.get("courseTitle"),
                        (String) row.get("courseSubTitle"),
                        (Integer) row.get("coursePrice"),
                        (String) row.get("courseImageUrl"),
                        CourseGenre.valueOf((String) row.get("courseGenre")),
                        SubjectCategory.valueOf((String) row.get("subjectCategory")),
                        CourseStatus.valueOf((String) row.get("courseStatus")),
                        ((ZonedDateTime) row.get("courseExpiredAt")).toLocalDateTime(),
                        ((ZonedDateTime) row.get("courseCreatedAt")).toLocalDateTime()
                ));
    }

    @Override
    public Mono<CourseDetailResult> getCourse(Long courseId) {
        String sql = """
                SELECT c.id as cCourseId,
                    c.teacher_id as cTeacherId,
                    c.course_series_id as cCourseSeriesId,
                    c.title as cCourseTitle,
                    c.sub_title as cCourseSubTitle,
                    c.price as cCoursePrice,
                    c.image_url as cCourseImageUrl,
                    c.description as cCourseDescription,
                    c.course_genre as cCourseGenre,
                    c.subject_category as subjectCategory,
                    c.course_status as cCourseStatus,
                    c.expired_at as cCourseExpiredAt,
                    c.created_at as cCourseCreatedAt
                FROM courses c
                WHERE c.id = :courseId;
                """;

        return databaseClient.sql(sql)
                .bind("courseId", courseId)
                .fetch()
                .one()
                .map(row -> new CourseDetailResult(
                        (Long) row.get("courseId"),
                        (Long) row.get("teacherId"),
                        (Long) row.get("courseSeriesId"),
                        (String) row.get("courseTitle"),
                        (String) row.get("courseSubTitle"),
                        (Integer) row.get("coursePrice"),
                        (String) row.get("courseImageUrl"),
                        (String) row.get("courseDescription"),
                        CourseGenre.valueOf((String) row.get("courseGenre")),
                        SubjectCategory.valueOf((String) row.get("subjectCategory")),
                        CourseStatus.valueOf((String) row.get("courseStatus")),
                        ((ZonedDateTime) row.get("courseExpiredAt")).toLocalDateTime(),
                        ((ZonedDateTime) row.get("courseCreatedAt")).toLocalDateTime()
                ));
    }

    @Override
    public Flux<CheckedRecentCourseOpenResult> checkRecentCourseOpenListByTeacherIds(List<Long> teacherIds) {
        return redisTemplate.opsForHash().multiGet(PRODUCT_SERVICE_RECENT_COURSE_UPLOAD_BY_TEACHER_ID_HASH_KEY, teacherIds.stream().map(String::valueOf).collect(Collectors.toList()))
                .flatMapMany(Flux::fromIterable)
                .flatMap(it -> Flux.just(String.valueOf(it)))
                .flatMap(json -> Mono.fromCallable(() ->
                        objectMapper.readValue(json, CheckedRecentCourseOpenResult.class).updateIsRecentCourseOpenTrue()
                ));
    }

    @Override
    public Flux<CourseResult> getCourseListByCourseSeriesIds(List<Long> courseSeriesIds) {
        String sql = """
                SELECT c.id as courseId,
                    c.teacher_id as teacherId,
                    c.course_title as courseTitle,
                    c.course_sub_title as courseSubTitle,
                    c.course_price as coursePrice,
                    c.course_image_url as courseImageUrl,
                    c.course_genre as courseGenre,
                    c.subject_category as subjectCategory,
                    c.course_status as courseStatus,
                    c.course_expired_at as courseExpiredAt,
                    c.course_created_at as courseCreatedAt
                FROM courses c
                WHERE c.course_series_id IN (:courseSeriesIds);
                """;

        return databaseClient.sql(sql)
                .bind("courseSeriesIds", courseSeriesIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new CourseResult(
                        (Long) row.get("courseId"),
                        (Long) row.get("teacherId"),
                        (String) row.get("courseTitle"),
                        (String) row.get("courseSubTitle"),
                        (Integer) row.get("coursePrice"),
                        (String) row.get("courseImageUrl"),
                        CourseGenre.valueOf((String) row.get("courseGenre")),
                        SubjectCategory.valueOf((String) row.get("subjectCategory")),
                        CourseStatus.valueOf((String) row.get("courseStatus")),
                        ((ZonedDateTime) row.get("courseExpiredAt")).toLocalDateTime(),
                        ((ZonedDateTime) row.get("courseCreatedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Flux<CourseResult> getCourseListByTeacherIds(List<Long> teacherIds) {
        String sql = """
                SELECT c.id as courseId,
                    c.teacher_id as teacherId,
                    c.course_title as courseTitle,
                    c.course_sub_title as courseSubTitle,
                    c.course_price as coursePrice,
                    c.course_image_url as courseImageUrl,
                    c.course_genre as courseGenre,
                    c.subject_category as subjectCategory,
                    c.course_status as courseStatus,
                    c.course_expired_at as courseExpiredAt,
                    c.course_created_at as courseCreatedAt
                FROM courses c
                WHERE c.teacher_id IN (:teacherIds);
                """;

        return databaseClient.sql(sql)
                .bind("teacherIds", teacherIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new CourseResult(
                        (Long) row.get("courseId"),
                        (Long) row.get("teacherId"),
                        (String) row.get("courseTitle"),
                        (String) row.get("courseSubTitle"),
                        (Integer) row.get("coursePrice"),
                        (String) row.get("courseImageUrl"),
                        CourseGenre.valueOf((String) row.get("courseGenre")),
                        SubjectCategory.valueOf((String) row.get("subjectCategory")),
                        CourseStatus.valueOf((String) row.get("courseStatus")),
                        ((ZonedDateTime) row.get("courseExpiredAt")).toLocalDateTime(),
                        ((ZonedDateTime) row.get("courseCreatedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Mono<CourseCountResult> getCourseCount() {
        String sql = """
                SELECT c.course_status as courseStatus,
                    COUNT(*) as statusCount
                FROM courses c
                GROUP BY c.course_status;
                """;
        return databaseClient.sql(sql)
                .fetch()
                .all()
                .collectList()
                .map(rowList -> {
                    Long all = 0L;
                    Long operatorCnt = 0L;
                    Long notOperatorCnt = 0L;
                    for (Map<String, Object> map: rowList) {
                        if (CourseStatus.valueOf((String) map.get("courseStatus")).equals(CourseStatus.REGISTERED)) {
                            operatorCnt = (Long) map.get("statusCount");
                        } else if (CourseStatus.valueOf((String) map.get("courseStatus")).equals(CourseStatus.UNREGISTERED)) {
                            notOperatorCnt = (Long) map.get("statusCount");
                        }
                    }
                    all = operatorCnt + notOperatorCnt;
                    return new CourseCountResult(all, operatorCnt, notOperatorCnt);
                });
    }
}
