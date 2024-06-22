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
                SELECT t.id as teacherId
                FROM teachers t
                WHERE t.teaching_genre = :teachingGenre;
                """;

        return databaseClient.sql(sql)
                .bind("teachingGenre", teachingGenre)
                .fetch()
                .all()
                .flatMap(row -> Flux.just((Long) row.get("teacherId")));
    }

    @Override
    public Flux<TeacherResult> getTeacherListByTeachingGenre(TeachingGenre teachingGenre) {
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
                WHERE t.teaching_genre = :teachingGenre;
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
    public Mono<TeacherResult> getSimpleTeacher(Long teacherId) {
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
                WHERE t.id = :teacherId;
                """;

        return databaseClient.sql(sql)
                .bind("teacherId", teacherId)
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
    public Mono<TeacherDetailResult> getTeacher(Long teacherId) {
        String sql = """
                SELECT t.id as teacherId,
                    t.teacher_rank as teacherRank,
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
                        (Long) row.get("teacherId"),
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
