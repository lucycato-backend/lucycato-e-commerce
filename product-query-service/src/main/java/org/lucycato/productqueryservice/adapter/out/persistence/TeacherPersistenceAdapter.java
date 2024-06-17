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

@PersistenceAdapter
@RequiredArgsConstructor
public class TeacherPersistenceAdapter implements TeacherPort {
    private final DatabaseClient databaseClient;

    @Override
    public Flux<Long> getTeacherIdsByTeachingGenre(TeachingGenre teachingGenre) {
        String sql = """
                SELECT id
                FROM teachers
                WHERE genre = :teachingGenre;
                """;

        return databaseClient.sql(sql)
                .bind("teachingGenre", teachingGenre)
                .fetch()
                .all()
                .flatMap(row -> Flux.just((Long) row.get("id")));
    }

    @Override
    public Flux<TeacherResult> getTeacherListByTeachingGenre(TeachingGenre teachingGenre) {
        String sql = """
                SELECT id,
                    rank,
                    name,
                    slogan,
                    image_url,
                    genre,
                    status,
                    created_at
                FROM teachers
                WHERE genre = :teachingGenre;
                """;

        return databaseClient.sql(sql)
                .bind("teachingGenre", teachingGenre)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TeacherResult(
                        (Long) row.get("id"),
                        (Integer) row.get("rank"),
                        (String) row.get("name"),
                        (String) row.get("slogan"),
                        (String) row.get("image_url"),
                        TeachingGenre.valueOf((String) row.get("genre")),
                        TeacherStatus.valueOf((String) row.get("status")),
                        ((ZonedDateTime) row.get("created_at")).toLocalDateTime()
                )));
    }

    @Override
    public Mono<TeacherResult> getSimpleTeacher(Long teacherId) {
        String sql = """
                SELECT id,
                    rank,
                    name,
                    slogan,
                    image_url,
                    genre,
                    status,
                    created_at
                FROM teachers
                WHERE id = :teacherId;
                """;

        return databaseClient.sql(sql)
                .bind("teacherId", teacherId)
                .fetch()
                .one()
                .map(row -> new TeacherResult(
                        (Long) row.get("id"),
                        (Integer) row.get("rank"),
                        (String) row.get("name"),
                        (String) row.get("slogan"),
                        (String) row.get("image_url"),
                        TeachingGenre.valueOf((String) row.get("genre")),
                        TeacherStatus.valueOf((String) row.get("status")),
                        ((ZonedDateTime) row.get("created_at")).toLocalDateTime()
                ));
    }

    @Override
    public Mono<TeacherDetailResult> getTeacher(Long teacherId) {
        String sql = """
                SELECT id,
                    rank,
                    name,
                    slogan,
                    profile_description
                    image_url,
                    curriculum_image_url,
                    curriculum_video_url
                    genre,
                    status,
                    created_at
                FROM teachers
                WHERE id = :teacherId;
                """;

        return databaseClient.sql(sql)
                .bind("teacherId", teacherId)
                .fetch()
                .one()
                .map(row -> new TeacherDetailResult(
                        (Long) row.get("id"),
                        (Integer) row.get("rank"),
                        (String) row.get("name"),
                        (String) row.get("slogan"),
                        (String) row.get("profile_description"),
                        (String) row.get("image_url"),
                        (String) row.get("curriculum_image_url"),
                        (String) row.get("curriculum_video_url"),
                        TeachingGenre.valueOf((String) row.get("genre")),
                        TeacherStatus.valueOf((String) row.get("status")),
                        ((ZonedDateTime) row.get("created_at")).toLocalDateTime()
                ));
    }
}
