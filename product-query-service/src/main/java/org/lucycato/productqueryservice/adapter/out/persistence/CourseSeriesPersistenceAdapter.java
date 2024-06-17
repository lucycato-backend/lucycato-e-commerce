package org.lucycato.productqueryservice.adapter.out.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productqueryservice.application.port.out.CourseSeriesPort;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.CourseSeriesResult;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productqueryservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
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
        String sql = """
                SELECT id,
                    teacher_id,
                    image_url,
                    title,
                    description,
                    category,
                    status
                FROM course_series
                WHERE teacher_id IN (:teacherIds);
                """;

        return databaseClient.sql(sql)
                .bind("teacherIds", teacherIds)
                .fetch()
                .all()
                .map(row -> new CourseSeriesResult(
                        (Long) row.get("id"),
                        (Long) row.get("teacher_id"),
                        (String) row.get("image_url"),
                        (String) row.get("title"),
                        (String) row.get("description"),
                        CourseSeriesCategory.valueOf((String) row.get("category")),
                        CourseSeriesStatus.valueOf((String) row.get("status"))
                ));
    }

    @Override
    public Mono<CourseSeriesDetailResult> getCourseSeries(Long courseSeriesId) {
        String sql = """
                SELECT id,
                    teacher_id,
                    image_url,
                    title,
                    description,
                    explain_image_urls_json,
                    subject_category,
                    category,
                    status,
                    created_at
                FROM course_series
                WHERE id = :courseSeriesId;
                """;

        return databaseClient.sql(sql)
                .bind("courseSeriesId", courseSeriesId)
                .fetch()
                .one()
                .flatMap(row -> Mono.fromCallable(() -> objectMapper.readValue((String) row.get("explain_image_urls_json"), new TypeReference<List<String>>() {
                                }))
                                .map(explainList -> new CourseSeriesDetailResult(
                                        (Long) row.get("id"),
                                        (Long) row.get("teacher_id"),
                                        (String) row.get("image_url"),
                                        (String) row.get("title"),
                                        (String) row.get("description"),
                                        explainList,
                                        SubjectCategory.valueOf((String) row.get("subject_category")),
                                        CourseSeriesCategory.valueOf((String) row.get("category")),
                                        CourseSeriesStatus.valueOf((String) row.get("status")),
                                        ((ZonedDateTime) row.get("created_at")).toLocalDateTime()
                                ))
                );

    }

    @Override
    public Mono<CourseSeriesResult> getSimpleCourseSeries(Long courseSeriesId) {
        String sql = """
                SELECT id,
                    teacher_id,
                    image_url,
                    title,
                    description,
                    category,
                    status
                FROM course_series
                WHERE id = :courseSeriesId;
                """;

        return databaseClient.sql(sql)
                .bind("courseSeriesId", courseSeriesId)
                .fetch()
                .one()
                .map(row -> new CourseSeriesResult(
                        (Long) row.get("id"),
                        (Long) row.get("teacher_id"),
                        (String) row.get("image_url"),
                        (String) row.get("title"),
                        (String) row.get("description"),
                        CourseSeriesCategory.valueOf((String) row.get("category")),
                        CourseSeriesStatus.valueOf((String) row.get("status"))
                ));
    }

    @Override
    public Mono<Long> getCourseSeriesCount(Long teacherId) {
        String sql = """
                SELECT COUNT(*) as courseSeriesCnt
                FROM course_series
                WHERE teacher_id = : teacherId;
                """;

        return databaseClient.sql(sql)
                .bind("teacherId", teacherId)
                .fetch()
                .one()
                .map(row -> (Long) row.get("courseSeriesCnt"));
    }
}
