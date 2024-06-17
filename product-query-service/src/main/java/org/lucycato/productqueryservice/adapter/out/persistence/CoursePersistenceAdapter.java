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
                SELECT id,
                    teacher_id,
                    title,
                    sub_title,
                    price,
                    image_url,
                    course_genre,
                    subject_category,
                    course_status,
                    expired_at,
                    created_at
                FROM courses
                WHERE id = :courseId;
                """;

        return databaseClient.sql(sql)
                .bind("courseId", courseId)
                .fetch()
                .one()
                .map(row -> new CourseResult(
                        (Long) row.get("id"),
                        (Long) row.get("teacher_id"),
                        (String) row.get("title"),
                        (String) row.get("sub_title"),
                        (Integer) row.get("price"),
                        (String) row.get("image_url"),
                        CourseGenre.valueOf((String) row.get("course_genre")),
                        SubjectCategory.valueOf((String) row.get("subject_category")),
                        CourseStatus.valueOf((String) row.get("course_status")),
                        ((ZonedDateTime) row.get("expired_at")).toLocalDateTime(),
                        ((ZonedDateTime) row.get("created_at")).toLocalDateTime()
                ));
    }

    @Override
    public Mono<CourseDetailResult> getCourse(Long courseId) {
        String sql = """
                SELECT *
                FROM courses
                WHERE id = :courseId;
                """;

        return databaseClient.sql(sql)
                .bind("courseId", courseId)
                .fetch()
                .one()
                .map(row -> new CourseDetailResult(
                        (Long) row.get("id"),
                        (Long) row.get("teacher_id"),
                        (Long) row.get("course_series_id"),
                        (String) row.get("title"),
                        (String) row.get("sub_title"),
                        (Integer) row.get("price"),
                        (String) row.get("image_url"),
                        (String) row.get("description"),
                        CourseGenre.valueOf((String) row.get("course_genre")),
                        SubjectCategory.valueOf((String) row.get("subject_category")),
                        CourseStatus.valueOf((String) row.get("course_status")),
                        ((ZonedDateTime) row.get("expired_at")).toLocalDateTime(),
                        ((ZonedDateTime) row.get("created_at")).toLocalDateTime()
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
                SELECT id,
                    teacher_id,
                    title,
                    sub_title,
                    price,
                    image_url,
                    course_genre,
                    subject_category,
                    course_status,
                    expired_at,
                    created_at
                FROM courses
                WHERE course_series_id IN (:courseSeriesIds);
                """;

        return databaseClient.sql(sql)
                .bind("courseSeriesIds", courseSeriesIds)
                .fetch()
                .all()
                .map(row -> new CourseResult(
                        (Long) row.get("id"),
                        (Long) row.get("teacher_id"),
                        (String) row.get("title"),
                        (String) row.get("sub_title"),
                        (Integer) row.get("price"),
                        (String) row.get("image_url"),
                        CourseGenre.valueOf((String) row.get("course_genre")),
                        SubjectCategory.valueOf((String) row.get("subject_category")),
                        CourseStatus.valueOf((String) row.get("course_status")),
                        ((ZonedDateTime) row.get("expired_at")).toLocalDateTime(),
                        ((ZonedDateTime) row.get("created_at")).toLocalDateTime()
                ));
    }

    @Override
    public Flux<CourseResult> getCourseListByTeacherIds(List<Long> teacherIds) {
        String sql = """
                SELECT id,
                    teacher_id,
                    title,
                    sub_title,
                    price,
                    image_url,
                    curse_genre,
                    subject_category,
                    course_status,
                    expired_at,
                    created_at
                FROM courses
                WHERE teacher_id IN (:teacherIds);
                """;

        return databaseClient.sql(sql)
                .bind("teacherIds", teacherIds)
                .fetch()
                .all()
                .map(row -> new CourseResult(
                        (Long) row.get("id"),
                        (Long) row.get("teacher_id"),
                        (String) row.get("title"),
                        (String) row.get("sub_title"),
                        (Integer) row.get("price"),
                        (String) row.get("image_url"),
                        CourseGenre.valueOf((String) row.get("curse_genre")),
                        SubjectCategory.valueOf((String) row.get("subject_category")),
                        CourseStatus.valueOf((String) row.get("course_status")),
                        ((ZonedDateTime) row.get("expired_at")).toLocalDateTime(),
                        ((ZonedDateTime) row.get("created_at")).toLocalDateTime()
                ));
    }

    @Override
    public Mono<CourseCountResult> getCourseCount() {
        String sql = """
                SELECT course_status, COUNT(*) as statusCount
                FROM courses
                GROUP BY course_status;
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
                        if (CourseStatus.valueOf((String) map.get("course_status")).equals(CourseStatus.REGISTERED)) {
                            operatorCnt = (Long) map.get("statusCount");
                        } else if (CourseStatus.valueOf((String) map.get("course_status")).equals(CourseStatus.UNREGISTERED)) {
                            notOperatorCnt = (Long) map.get("statusCount");
                        }
                    }
                    all = operatorCnt + notOperatorCnt;
                    return new CourseCountResult(all, operatorCnt, notOperatorCnt);
                });
    }
}
