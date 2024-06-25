package org.lucycato.productqueryservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productqueryservice.application.port.out.TeacherPort;
import org.lucycato.productqueryservice.application.port.out.result.TeacherDetailResult;
import org.lucycato.productqueryservice.application.port.out.result.TeacherResult;
import org.lucycato.productqueryservice.domain.enums.TeacherStatus;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeacherPersistenceAdapter implements TeacherPort {
    private final DatabaseClient databaseClient;

    @Override
    public Flux<Long> getTeacherIds() {
        String sql = """
                SELECT t.id as teacherId
                FROM teachers t
                """;

        return databaseClient.sql(sql)
                .fetch()
                .all()
                .flatMap(row -> Flux.just((Long) row.get("teacherId")));
    }

    @Override
    public Flux<Long> getTeacherIds(TeachingGenre teachingGenre) {
        String sql = """
                SELECT t.id as teacherId
                FROM teachers t
                WHERE t.teaching_genre = :teachingGenre
                """;

        return databaseClient.sql(sql)
                .bind("teachingGenre", teachingGenre)
                .fetch()
                .all()
                .flatMap(row -> Flux.just((Long) row.get("teacherId")));
    }

    @Override
    public Flux<TeacherResult> getTeacherListByTeacherIds(List<Long> teacherIds) {
        if (teacherIds == null || teacherIds.isEmpty()) {
            return Flux.empty();
        }
        String sql = """
                SELECT t.id as teacherId,
                    t.teacher_rank as teacherRank,
                    t.teacher_name as teacherName,
                    t.teacher_slogan as teacherSlogan,
                    t.teacher_image_url as teacherImageUrl,
                    t.teaching_genre as teachingGenre,
                    t.teacher_status as teacherStatus,
                    t.teacher_created_at as teacherCreatedAt
                FROM teachers t
                WHERE t.id IN (:teacherIds)
                """;

        return databaseClient.sql(sql)
                .bind("teacherIds", teacherIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TeacherResult(
                        (Long) row.get("teacherId"),
                        (Integer) row.get("teacherRank"),
                        (String) row.get("teacherName"),
                        (String) row.get("teacherSlogan"),
                        (String) row.get("teacherImageUrl"),
                        TeachingGenre.valueOf((String) row.get("teachingGenre")),
                        TeacherStatus.valueOf((String) row.get("teacherStatus")),
                        ((ZonedDateTime) row.get("teacherCreatedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Flux<TeacherResult> getTeacherList() {
        String sql = """
                SELECT t.id as teacherId,
                    t.teacher_rank as teacherRank,
                    t.teacher_name as teacherName,
                    t.teacher_slogan as teacherSlogan,
                    t.teacher_image_url as teacherImageUrl,
                    t.teaching_genre as teachingGenre,
                    t.teacher_status as teacherStatus,
                    t.teacher_created_at as teacherCreatedAt
                FROM teachers t
                """;

        return databaseClient.sql(sql)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TeacherResult(
                        (Long) row.get("teacherId"),
                        (Integer) row.get("teacherRank"),
                        (String) row.get("teacherName"),
                        (String) row.get("teacherSlogan"),
                        (String) row.get("teacherImageUrl"),
                        TeachingGenre.valueOf((String) row.get("teachingGenre")),
                        TeacherStatus.valueOf((String) row.get("teacherStatus")),
                        ((ZonedDateTime) row.get("teacherCreatedAt")).toLocalDateTime()
                )));
    }


    @Override
    public Flux<TeacherResult> getTeacherList(TeachingGenre teachingGenre) {
        String sql = """
                SELECT t.id as teacherId,
                    t.teacher_rank as teacherRank,
                    t.teacher_name as teacherName,
                    t.teacher_slogan as teacherSlogan,
                    t.teacher_image_url as teacherImageUrl,
                    t.teaching_genre as teachingGenre,
                    t.teacher_status as teacherStatus,
                    t.teacher_created_at as teacherCreatedAt
                FROM teachers t
                WHERE t.teaching_genre = :teachingGenre
                """;

        return databaseClient.sql(sql)
                .bind("teachingGenre", teachingGenre)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TeacherResult(
                        (Long) row.get("teacherId"),
                        (Integer) row.get("teacherRank"),
                        (String) row.get("teacherName"),
                        (String) row.get("teacherSlogan"),
                        (String) row.get("teacherImageUrl"),
                        TeachingGenre.valueOf((String) row.get("teachingGenre")),
                        TeacherStatus.valueOf((String) row.get("teacherStatus")),
                        ((ZonedDateTime) row.get("teacherCreatedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Flux<TeacherResult> getTeacherList(TeachingGenre teachingGenre, Integer page, Integer size) {
        Integer start = page * size;
        String sql = """
                SELECT t.id as teacherId,
                    t.teacher_rank as teacherRank,
                    t.teacher_name as teacherName,
                    t.teacher_slogan as teacherSlogan,
                    t.teacher_image_url as teacherImageUrl,
                    t.teaching_genre as teachingGenre,
                    t.teacher_status as teacherStatus,
                    t.teacher_created_at as teacherCreatedAt
                FROM teachers t
                INNER JOIN (
                    SELECT id
                    FROM teachers
                    ORDER BY id
                    LIMIT :start, :size
                ) as temp
                ON t.id = temp.id
                WHERE t.teaching_genre = :teachingGenre
                """;

        return databaseClient.sql(sql)
                .bind("teachingGenre", teachingGenre)
                .bind("start", start)
                .bind("size", size)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TeacherResult(
                        (Long) row.get("teacherId"),
                        (Integer) row.get("teacherRank"),
                        (String) row.get("teacherName"),
                        (String) row.get("teacherSlogan"),
                        (String) row.get("teacherImageUrl"),
                        TeachingGenre.valueOf((String) row.get("teachingGenre")),
                        TeacherStatus.valueOf((String) row.get("teacherStatus")),
                        ((ZonedDateTime) row.get("teacherCreatedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Mono<TeacherResult> getSimpleTeacherByTeacherId(Long teacherId) {
        String sql = """
                SELECT t.teacher_rank as teacherRank,
                    t.teacher_name as teacherName,
                    t.teacher_slogan as teacherSlogan,
                    t.teacher_image_url as teacherImageUrl,
                    t.teaching_genre as teachingGenre,
                    t.teacher_status as teacherStatus,
                    t.teacher_created_at as teacherCreatedAt
                FROM teachers t
                WHERE t.id = :teacherId;
                """;

        return databaseClient.sql(sql)
                .bind("teacherId", teacherId)
                .fetch()
                .one()
                .map(row -> new TeacherResult(
                        teacherId,
                        (Integer) row.get("teacherRank"),
                        (String) row.get("teacherName"),
                        (String) row.get("teacherSlogan"),
                        (String) row.get("teacherImageUrl"),
                        TeachingGenre.valueOf((String) row.get("teachingGenre")),
                        TeacherStatus.valueOf((String) row.get("teacherStatus")),
                        ((ZonedDateTime) row.get("teacherCreatedAt")).toLocalDateTime()
                ));
    }

    @Override
    public Mono<TeacherResult> getSimpleTeacherByCourseSeriesId(Long courseSeriesId) {
        String sql = """
                SELECT t.id as teacherId,
                    t.teacher_rank as teacherRank,
                    t.teacher_name as teacherName,
                    t.teacher_slogan as teacherSlogan,
                    t.teacher_image_url as teacherImageUrl,
                    t.teaching_genre as teachingGenre,
                    t.teacher_status as teacherStatus,
                    t.teacher_created_at as teacherCreatedAt
                FROM teachers t
                INNER JOIN course_series cs ON t.id = cs.teacher_id
                WHERE cs.id = :courseSeriesId;
                """;

        return databaseClient.sql(sql)
                .bind("courseSeriesId", courseSeriesId)
                .fetch()
                .one()
                .map(row -> new TeacherResult(
                        (Long) row.get("teacherId"),
                        (Integer) row.get("teacherRank"),
                        (String) row.get("teacherName"),
                        (String) row.get("teacherSlogan"),
                        (String) row.get("teacherImageUrl"),
                        TeachingGenre.valueOf((String) row.get("teachingGenre")),
                        TeacherStatus.valueOf((String) row.get("teacherStatus")),
                        ((ZonedDateTime) row.get("teacherCreatedAt")).toLocalDateTime()
                ));
    }

    @Override
    public Mono<TeacherDetailResult> getTeacherByTeacherId(Long teacherId) {
        String sql = """
                SELECT t.teacher_rank as teacherRank,
                    t.teacher_name as teacherName,
                    t.teacher_slogan as teacherSlogan,
                    t.teacher_profile_description as teacherProfileDescription,
                    t.teacher_image_url as teacherImageUrl,
                    t.teacher_curriculum_image_url as curriculumImageUrl,
                    t.teacher_curriculum_video_url as curriculumVideoUrl,
                    t.teaching_genre as teachingGenre,
                    t.teacher_status as teacherStatus,
                    t.teacher_created_at as teacherCreatedAt
                FROM teachers t
                WHERE t.id = :teacherId;
                """;

        return databaseClient.sql(sql)
                .bind("teacherId", teacherId)
                .fetch()
                .one()
                .map(row -> new TeacherDetailResult(
                        teacherId,
                        (Integer) row.get("teacherRank"),
                        (String) row.get("teacherName"),
                        (String) row.get("teacherSlogan"),
                        (String) row.get("teacherProfileDescription"),
                        (String) row.get("teacherImageUrl"),
                        (String) row.get("curriculumImageUrl"),
                        (String) row.get("curriculumVideoUrl"),
                        TeachingGenre.valueOf((String) row.get("teachingGenre")),
                        TeacherStatus.valueOf((String) row.get("teacherStatus")),
                        ((ZonedDateTime) row.get("teacherCreatedAt")).toLocalDateTime()
                ));


    }
}
