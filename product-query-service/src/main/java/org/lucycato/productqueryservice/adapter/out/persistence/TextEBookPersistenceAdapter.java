package org.lucycato.productqueryservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productqueryservice.application.port.out.TextEBookPort;
import org.lucycato.productqueryservice.application.port.out.result.CourseCountResult;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookCountResult;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookResult;
import org.lucycato.productqueryservice.domain.enums.*;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@PersistenceAdapter
@RequiredArgsConstructor
public class TextEBookPersistenceAdapter implements TextEBookPort {
    private final DatabaseClient databaseClient;

    @Override
    public Flux<TextEBookResult> getTextEBookListByCourseIds(List<Long> courseIds) {
        String sql = """
                SELECT id,
                    course_id,
                    e_book_unique_code,
                    image_url,
                    title,
                    description,
                    table_of_contents,
                    author,
                    publisher,
                    preview_download_url,
                    page,
                    subject_category,
                    teaching_genre,
                    status,
                    published_at
                FROM text_e_books
                WHERE course_id IN (:courseIds);
                """;

        return databaseClient.sql(sql)
                .bind("courseIds", courseIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TextEBookResult(
                        (Long) row.get("id"),
                        (Long) row.get("course_id"),
                        (String) row.get("e_book_unique_code"),
                        (String) row.get("image_url"),
                        (String) row.get("title"),
                        (String) row.get("description"),
                        (String) row.get("table_of_contents"),
                        (String) row.get("author"),
                        (String) row.get("publisher"),
                        (String) row.get("preview_download_url"),
                        (Integer) row.get("page"),
                        SubjectCategory.valueOf((String) row.get("subject_category")),
                        TeachingGenre.valueOf((String) row.get("teaching_genre")),
                        TextEBookStatus.valueOf((String) row.get("status")),
                        ((ZonedDateTime) row.get("published_at")).toLocalDateTime()
                )));
    }

    @Override
    public Flux<TextEBookResult> getTextEBookListByTeacherIds(List<Long> teacherIds) {
        String sql = """
                SELECT t.id,
                    c.teacher_id,
                    t.course_id,
                    t.e_book_unique_code,
                    t.image_url,
                    t.title,
                    t.description,
                    t.table_of_contents,
                    t.author,
                    t.publisher,
                    t.preview_download_url,
                    t.page,
                    t.subject_category,
                    t.teaching_genre,
                    t.status,
                    t.published_at
                FROM text_e_books t
                LEFT JOIN courses c ON t.course_id = c.id
                WHERE c.teacher_id IN (:teacherIds);
                """;

        return databaseClient.sql(sql)
                .bind("teacherIds", teacherIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TextEBookResult(
                        (Long) row.get("id"),
                        (Long) row.get("course_id"),
                        (String) row.get("e_book_unique_code"),
                        (String) row.get("image_url"),
                        (String) row.get("title"),
                        (String) row.get("description"),
                        (String) row.get("table_of_contents"),
                        (String) row.get("author"),
                        (String) row.get("publisher"),
                        (String) row.get("preview_download_url"),
                        (Integer) row.get("page"),
                        SubjectCategory.valueOf((String) row.get("subject_category")),
                        TeachingGenre.valueOf((String) row.get("teaching_genre")),
                        TextEBookStatus.valueOf((String) row.get("status")),
                        ((ZonedDateTime) row.get("published_at")).toLocalDateTime()
                )));
    }

    @Override
    public Flux<TextEBookResult> getTextEBookListByCourseSeriesIds(List<Long> courseSeriesIds) {
        String sql = """
                SELECT t.id,
                    c.course_series_id,
                    t.course_id,
                    t.e_book_unique_code,
                    t.image_url,
                    t.title,
                    t.description,
                    t.table_of_contents,
                    t.author,
                    t.publisher,
                    t.preview_download_url,
                    t.page,
                    t.subject_category,
                    t.teaching_genre,
                    t.status,
                    t.published_at
                FROM text_e_books t
                LEFT JOIN courses c ON t.course_id = c.id
                WHERE c.course_series_id IN (:courseSeriesIds);
                """;

        return databaseClient.sql(sql)
                .bind("teacherIds", courseSeriesIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TextEBookResult(
                        (Long) row.get("id"),
                        (Long) row.get("course_id"),
                        (String) row.get("e_book_unique_code"),
                        (String) row.get("image_url"),
                        (String) row.get("title"),
                        (String) row.get("description"),
                        (String) row.get("table_of_contents"),
                        (String) row.get("author"),
                        (String) row.get("publisher"),
                        (String) row.get("preview_download_url"),
                        (Integer) row.get("page"),
                        SubjectCategory.valueOf((String) row.get("subject_category")),
                        TeachingGenre.valueOf((String) row.get("teaching_genre")),
                        TextEBookStatus.valueOf((String) row.get("status")),
                        ((ZonedDateTime) row.get("published_at")).toLocalDateTime()
                )));
    }

    @Override
    public Mono<TextEBookCountResult> getTextEBookCountResult() {
        String sql = """
                SELECT status, COUNT(*) as statusCount
                FROM text_e_books
                GROUP BY status;
                """;
        return databaseClient.sql(sql)
                .fetch()
                .all()
                .collectList()
                .map(rowList -> {
                    Long operatorCnt = 0L;
                    Long notOperatorCnt = 0L;
                    for (Map<String, Object> map: rowList) {
                        if (TextEBookStatus.valueOf((String) map.get("status")).equals(TextEBookStatus.OPERATOR)) {
                            operatorCnt = (Long) map.get("statusCount");
                        } else if (TextEBookStatus.valueOf((String) map.get("status")).equals(TextEBookStatus.NOT_OPERATOR)) {
                            notOperatorCnt = (Long) map.get("statusCount");
                        }
                    }
                    return new TextEBookCountResult(operatorCnt, notOperatorCnt);
                });
    }
}
