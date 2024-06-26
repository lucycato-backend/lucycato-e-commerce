package org.lucycato.productqueryservice.adapter.out.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productqueryservice.application.port.out.CourseSeriesPort;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesResult;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class CourseSeriesPersistenceAdapter implements CourseSeriesPort {
    private final ObjectMapper objectMapper;

    private final DatabaseClient databaseClient;

    @Override
    public Flux<CourseSeriesResult> getCourseSeriesListByTeacherIds(List<Long> teacherIds) {
        if (teacherIds == null || teacherIds.isEmpty()) {
            return Flux.empty();
        }
        String sql = """
                SELECT cs.id as courseSeriesId,
                    cs.teacher_id as teacherId,
                    cs.course_series_image_url as courseSeriesImageUrl,
                    cs.course_series_title as courseSeriesTitle,
                    cs.course_series_description as courseSeriesDescription,
                    cs.course_series_category as courseSeriesCategory,
                    cs.course_series_status as courseSeriesStatus
                FROM course_series cs
                WHERE cs.teacher_id IN (:teacherIds);
                """;

        return databaseClient.sql(sql)
                .bind("teacherIds", teacherIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new CourseSeriesResult(
                        (Long) row.get("courseSeriesId"),
                        (Long) row.get("teacherId"),
                        (String) row.get("courseSeriesImageUrl"),
                        (String) row.get("courseSeriesTitle"),
                        (String) row.get("courseSeriesDescription"),
                        CourseSeriesCategory.valueOf((String) row.get("courseSeriesCategory")),
                        CourseSeriesStatus.valueOf((String) row.get("courseSeriesStatus"))
                )));
    }

    @Override
    public Flux<CourseSeriesResult> getCourseSeriesListByCourseIds(List<Long> courseIds) {
        if (courseIds == null || courseIds.isEmpty()) {
            return Flux.empty();
        }
        String sql = """
                SELECT cs.id as courseSeriesId,
                    cs.teacher_id as teacherId,
                    cs.course_series_image_url as courseSeriesImageUrl,
                    cs.course_series_title as courseSeriesTitle,
                    cs.course_series_description as courseSeriesDescription,
                    cs.course_series_category as courseSeriesCategory,
                    cs.course_series_status as courseSeriesStatus
                FROM course_series cs
                INNER JOIN courses c ON cs.id = c.course_series_id
                WHERE c.id IN (:courseIds);
                """;

        return databaseClient.sql(sql)
                .bind("courseIds", courseIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new CourseSeriesResult(
                        (Long) row.get("courseSeriesId"),
                        (Long) row.get("teacherId"),
                        (String) row.get("courseSeriesImageUrl"),
                        (String) row.get("courseSeriesTitle"),
                        (String) row.get("courseSeriesDescription"),
                        CourseSeriesCategory.valueOf((String) row.get("courseSeriesCategory")),
                        CourseSeriesStatus.valueOf((String) row.get("courseSeriesStatus"))
                )));
    }

    @Override
    public Flux<CourseSeriesResult> getCourseSeriesListByTextEBookIds(List<Long> textEBookIds) {
        if (textEBookIds == null || textEBookIds.isEmpty()) {
            return Flux.empty();
        }
        String sql = """
                SELECT cs.id as courseSeriesId,
                    cs.teacher_id as teacherId,
                    cs.course_series_image_url as courseSeriesImageUrl,
                    cs.course_series_title as courseSeriesTitle,
                    cs.course_series_description as courseSeriesDescription,
                    cs.course_series_category as courseSeriesCategory,
                    cs.course_series_status as courseSeriesStatus
                FROM course_series cs
                INNER JOIN courses c ON cs.id = c.course_series_id
                INNER JOIN text_e_books tb ON c.id = tb.course_id
                WHERE tb.id IN (:textEBookIds);
                """;

        return databaseClient.sql(sql)
                .bind("textEBookIds", textEBookIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new CourseSeriesResult(
                        (Long) row.get("courseSeriesId"),
                        (Long) row.get("teacherId"),
                        (String) row.get("courseSeriesImageUrl"),
                        (String) row.get("courseSeriesTitle"),
                        (String) row.get("courseSeriesDescription"),
                        CourseSeriesCategory.valueOf((String) row.get("courseSeriesCategory")),
                        CourseSeriesStatus.valueOf((String) row.get("courseSeriesStatus"))
                )));
    }

//    getCourseSeriesListByTextEBookIds

    @Override
    public Mono<CourseSeriesDetailResult> getCourseSeriesByCourseSeriesId(Long courseSeriesId) {
        String sql = """
                SELECT cs.teacher_id as teacherId,
                    cs.course_series_image_url as courseSeriesImageUrl,
                    cs.course_series_title as courseSeriesTitle,
                    cs.course_series_description as courseSeriesDescription,
                    cs.course_series_explain_image_urls_json as courseSeriesExplainImageUrlsJson,
                    cs.subject_category as subjectCategory,
                    cs.course_series_category as courseSeriesCategory,
                    cs.course_series_status as courseSeriesStatus,
                    cs.course_series_created_at as courseSeriesCreatedAt
                FROM course_series cs
                WHERE cs.id = :courseSeriesId;
                """;

        return databaseClient.sql(sql)
                .bind("courseSeriesId", courseSeriesId)
                .fetch()
                .one()
                .flatMap(row -> Mono.fromCallable(() -> objectMapper.readValue((String) row.get("courseSeriesExplainImageUrlsJson"), new TypeReference<List<String>>() {
                                }))
                                .map(explainList -> new CourseSeriesDetailResult(
                                        courseSeriesId,
                                        (Long) row.get("teacherId"),
                                        (String) row.get("courseSeriesImageUrl"),
                                        (String) row.get("courseSeriesTitle"),
                                        (String) row.get("courseSeriesDescription"),
                                        explainList,
                                        SubjectCategory.valueOf((String) row.get("subjectCategory")),
                                        CourseSeriesCategory.valueOf((String) row.get("courseSeriesCategory")),
                                        CourseSeriesStatus.valueOf((String) row.get("courseSeriesStatus")),
                                        ((ZonedDateTime) row.get("courseSeriesCreatedAt")).toLocalDateTime()
                                ))
                );

    }

    @Override
    public Flux<CourseSeriesResult> getCourseSeriesList(TeachingGenre teachingGenre) {
        String sql = """
                SELECT cs.id as courseSeriesId,
                    cs.teacher_id as teacherId,
                    cs.course_series_image_url as courseSeriesImageUrl,
                    cs.course_series_title as courseSeriesTitle,
                    cs.course_series_description as courseSeriesDescription,
                    cs.course_series_category as courseSeriesCategory,
                    cs.course_series_status as courseSeriesStatus
                FROM course_series cs
                INNER JOIN teachers t ON t.id = cs.teacher_id
                WHERE t.teaching_genre = :teachingGenre
                """;

        return databaseClient.sql(sql)
                .bind("teachingGenre", teachingGenre)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new CourseSeriesResult(
                        (Long) row.get("courseSeriesId"),
                        (Long) row.get("teacherId"),
                        (String) row.get("courseSeriesImageUrl"),
                        (String) row.get("courseSeriesTitle"),
                        (String) row.get("courseSeriesDescription"),
                        CourseSeriesCategory.valueOf((String) row.get("courseSeriesCategory")),
                        CourseSeriesStatus.valueOf((String) row.get("courseSeriesStatus"))
                )));
    }

    @Override
    public Mono<CourseSeriesResult> getSimpleCourseSeriesByCourseSeriesId(Long courseSeriesId) {
        String sql = """
                SELECT cs.teacher_id as teacherId,
                    cs.course_series_image_url as courseSeriesImageUrl,
                    cs.course_series_title as courseSeriesTitle,
                    cs.course_series_description as courseSeriesDescription,
                    cs.course_series_category as courseSeriesCategory,
                    cs.course_series_status as courseSeriesStatus
                FROM course_series cs
                WHERE cs.id = :courseSeriesId;
                """;

        return databaseClient.sql(sql)
                .bind("courseSeriesId", courseSeriesId)
                .fetch()
                .one()
                .map(row -> new CourseSeriesResult(
                        courseSeriesId,
                        (Long) row.get("teacherId"),
                        (String) row.get("courseSeriesImageUrl"),
                        (String) row.get("courseSeriesTitle"),
                        (String) row.get("courseSeriesDescription"),
                        CourseSeriesCategory.valueOf((String) row.get("courseSeriesCategory")),
                        CourseSeriesStatus.valueOf((String) row.get("courseSeriesStatus"))
                ));
    }

    @Override
    public Mono<Long> getCourseSeriesCountByTeacherId(Long teacherId) {
        if (teacherId == null) {
            throw new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND);
        }
        String sql = """
                SELECT COUNT(*) as courseSeriesCnt
                FROM course_series cs
                WHERE cs.teacher_id = :teacherId;
                """;

        return databaseClient.sql(sql)
                .bind("teacherId", teacherId)
                .fetch()
                .one()
                .map(row -> (Long) row.get("courseSeriesCnt"));
    }
}
