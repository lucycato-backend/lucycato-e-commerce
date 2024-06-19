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
                SELECT tb.id as textEBookId,
                    tb.course_id as courseId,
                    tb.text_e_book_unique_code as textEBookUniqueCode,
                    tb.text_e_book_image_url as textEBookImageUrl,
                    tb.text_e_book_title as textEBookTitle,
                    tb.text_e_book_description as textEBookDescription,
                    tb.text_e_book_table_of_contents as textEBookTableOfContents,
                    tb.text_e_book_author as textEBookAuthor,
                    tb.text_e_book_publisher as textEBookPublisher,
                    tb.text_e_book_preview_download_url as textEBookPreviewDownloadUrl,
                    tb.text_e_book_page as textEBookPage,
                    tb.subject_category as subjectCategory,
                    tb.text_e_book_genre as teachingGenre,
                    tb.text_e_book_status as textEBookStatus,
                    tb.text_e_book_published_at as textEBookPublishedAt
                FROM text_e_books tb
                WHERE tb.course_id IN (:courseIds);
                """;

        return databaseClient.sql(sql)
                .bind("courseIds", courseIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TextEBookResult(
                        (Long) row.get("textEBookId"),
                        (Long) row.get("courseId"),
                        (String) row.get("textEBookUniqueCode"),
                        (String) row.get("textEBookImageUrl"),
                        (String) row.get("textEBookTitle"),
                        (String) row.get("textEBookDescription"),
                        (String) row.get("textEBookTableOfContents"),
                        (String) row.get("textEBookAuthor"),
                        (String) row.get("textEBookPublisher"),
                        (String) row.get("textEBookPreviewDownloadUrl"),
                        (Integer) row.get("textEBookPage"),
                        SubjectCategory.valueOf((String) row.get("subjectCategory")),
                        TeachingGenre.valueOf((String) row.get("teachingGenre")),
                        TextEBookStatus.valueOf((String) row.get("textEBookStatus")),
                        ((ZonedDateTime) row.get("textEBookPublishedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Flux<TextEBookResult> getTextEBookListByTeacherIds(List<Long> teacherIds) {
        String sql = """
                SELECT t.id as textEBookId,
                    c.teacher_id as teacherId,
                    tb.course_id as courseId,
                    tb.text_e_book_unique_code as textEBookUniqueCode,
                    tb.text_e_book_image_url as textEBookImageUrl,
                    tb.text_e_book_title as textEBookTitle,
                    tb.text_e_book_description as textEBookDescription,
                    tb.text_e_book_table_of_contents as textEBookTableOfContents,
                    tb.text_e_book_author as textEBookAuthor,
                    tb.text_e_book_publisher as textEBookPublisher,
                    tb.text_e_book_preview_download_url as textEBookPreviewDownloadUrl,
                    tb.text_e_book_page as textEBookPage,
                    tb.subject_category as subjectCategory,
                    tb.text_e_book_genre as teachingGenre,
                    tb.text_e_book_status as textEBookStatus,
                    tb.text_e_book_published_at as textEBookPublishedAt
                FROM text_e_books tb
                LEFT JOIN courses c ON tb.course_id = c.id
                WHERE c.teacher_id IN (:teacherIds);
                """;

        return databaseClient.sql(sql)
                .bind("teacherIds", teacherIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TextEBookResult(
                        (Long) row.get("textEBookId"),
                        (Long) row.get("courseId"),
                        (String) row.get("textEBookUniqueCode"),
                        (String) row.get("textEBookImageUrl"),
                        (String) row.get("textEBookTitle"),
                        (String) row.get("textEBookDescription"),
                        (String) row.get("textEBookTableOfContents"),
                        (String) row.get("textEBookAuthor"),
                        (String) row.get("textEBookPublisher"),
                        (String) row.get("textEBookPreviewDownloadUrl"),
                        (Integer) row.get("textEBookPage"),
                        SubjectCategory.valueOf((String) row.get("subjectCategory")),
                        TeachingGenre.valueOf((String) row.get("teachingGenre")),
                        TextEBookStatus.valueOf((String) row.get("textEBookStatus")),
                        ((ZonedDateTime) row.get("textEBookPublishedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Flux<TextEBookResult> getTextEBookListByCourseSeriesIds(List<Long> courseSeriesIds) {
        String sql = """
                SELECT t.id as teacherId,
                    c.course_series_id as courseSeriesId,
                    tb.course_id as courseId,
                    tb.text_e_book_unique_code as textEBookUniqueCode,
                    tb.text_e_book_image_url as textEBookImageUrl,
                    tb.text_e_book_title as textEBookTitle,
                    tb.text_e_book_description as textEBookDescription,
                    tb.text_e_book_table_of_contents as textEBookTableOfContents,
                    tb.text_e_book_author as textEBookAuthor,
                    tb.text_e_book_publisher as textEBookPublisher,
                    tb.text_e_book_preview_download_url as textEBookPreviewDownloadUrl,
                    tb.text_e_book_page as textEBookPage,
                    tb.subject_category as subjectCategory,
                    tb.text_e_book_genre as teachingGenre,
                    tb.text_e_book_status as textEBookStatus,
                    tb.text_e_book_published_at as textEBookPublishedAt
                FROM text_e_books tb
                LEFT JOIN courses c ON tb.course_id = c.id
                WHERE c.course_series_id IN (:courseSeriesIds);
                """;

        return databaseClient.sql(sql)
                .bind("teacherIds", courseSeriesIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TextEBookResult(
                        (Long) row.get("textEBookId"),
                        (Long) row.get("courseId"),
                        (String) row.get("textEBookUniqueCode"),
                        (String) row.get("textEBookImageUrl"),
                        (String) row.get("textEBookTitle"),
                        (String) row.get("textEBookDescription"),
                        (String) row.get("textEBookTableOfContents"),
                        (String) row.get("textEBookAuthor"),
                        (String) row.get("textEBookPublisher"),
                        (String) row.get("textEBookPreviewDownloadUrl"),
                        (Integer) row.get("textEBookPage"),
                        SubjectCategory.valueOf((String) row.get("subjectCategory")),
                        TeachingGenre.valueOf((String) row.get("teachingGenre")),
                        TextEBookStatus.valueOf((String) row.get("textEBookStatus")),
                        ((ZonedDateTime) row.get("textEBookPublishedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Mono<TextEBookCountResult> getTextEBookCountResult() {
        String sql = """
                SELECT tb.teacher_status as teacherStatus,
                    COUNT(*) as statusCount
                FROM text_e_books tb
                GROUP BY tb.teacher_status;
                """;
        return databaseClient.sql(sql)
                .fetch()
                .all()
                .collectList()
                .map(rowList -> {
                    Long operatorCnt = 0L;
                    Long notOperatorCnt = 0L;
                    for (Map<String, Object> map: rowList) {
                        if (TextEBookStatus.valueOf((String) map.get("teacherStatus")).equals(TextEBookStatus.OPERATOR)) {
                            operatorCnt = (Long) map.get("statusCount");
                        } else if (TextEBookStatus.valueOf((String) map.get("teacherStatus")).equals(TextEBookStatus.NOT_OPERATOR)) {
                            notOperatorCnt = (Long) map.get("statusCount");
                        }
                    }
                    return new TextEBookCountResult(operatorCnt, notOperatorCnt);
                });
    }
}
