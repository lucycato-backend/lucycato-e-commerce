package org.lucycato.productqueryservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productqueryservice.application.port.out.TextEBookPort;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookCountResult;
import org.lucycato.productqueryservice.application.port.out.result.TextEBookResult;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
import org.lucycato.productqueryservice.domain.enums.TextEBookStatus;
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

    //exist
    //group by fileSort
    // cross join

    // paging
    // query dsl vs 병목 vs concurrency hash map 사용
    // 성능테스트 해보기
    // db 정리하기....
    // one to one lazy loading x -> N + 1 문제 발생, 불필요한 attribute 가져온다.

    @Override
    public Flux<TextEBookResult> getTextEBookListByCourseIds(List<Long> courseIds) {
        if (courseIds == null || courseIds.isEmpty()) {
            return Flux.empty();
        }
        String sql = """
                SELECT tb.id as textEBookId,
                    tb.course_id as courseId,
                    tb.textebook_unique_code as textEBookUniqueCode,
                    tb.textebook_image_url as textEBookImageUrl,
                    tb.textebook_title as textEBookTitle,
                    tb.textebook_description as textEBookDescription,
                    tb.textebook_table_of_contents as textEBookTableOfContents,
                    tb.textebook_author as textEBookAuthor,
                    tb.textebook_publisher as textEBookPublisher,
                    tb.textebook_preview_download_url as textEBookPreviewDownloadUrl,
                    tb.textebook_page as textEBookPage,
                    tb.subject_category as subjectCategory,
                    tb.textebook_status as textEBookStatus,
                    tb.textebook_published_at as textEBookPublishedAt
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
                        0L,
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
                        TextEBookStatus.valueOf((String) row.get("textEBookStatus")),
                        ((ZonedDateTime) row.get("textEBookPublishedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Flux<TextEBookResult> getTextEBookListByTeacherIds(List<Long> teacherIds) {
        if (teacherIds == null || teacherIds.isEmpty()) {
            return Flux.empty();
        }
        String sql = """
                SELECT tb.id as textEBookId,
                    tb.course_id as courseId,
                    cs.id as courseSeriesId,
                    tb.textebook_unique_code as textEBookUniqueCode,
                    tb.textebook_image_url as textEBookImageUrl,
                    tb.textebook_title as textEBookTitle,
                    tb.textebook_description as textEBookDescription,
                    tb.textebook_table_of_contents as textEBookTableOfContents,
                    tb.textebook_author as textEBookAuthor,
                    tb.textebook_publisher as textEBookPublisher,
                    tb.textebook_preview_download_url as textEBookPreviewDownloadUrl,
                    tb.textebook_page as textEBookPage,
                    tb.subject_category as subjectCategory,
                    tb.textebook_status as textEBookStatus,
                    tb.textebook_published_at as textEBookPublishedAt
                FROM text_e_books tb
                INNER JOIN courses c ON c.id = tb.course_id
                INNER JOIN course_series cs ON cs.id = c.course_series_id
                WHERE cs.teacher_id IN (:teacherIds);
                """;

        return databaseClient.sql(sql)
                .bind("teacherIds", teacherIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TextEBookResult(
                        (Long) row.get("textEBookId"),
                        (Long) row.get("courseId"),
                        (Long) row.get("courseSeriesId"),
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
                        TextEBookStatus.valueOf((String) row.get("textEBookStatus")),
                        ((ZonedDateTime) row.get("textEBookPublishedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Flux<TextEBookResult> getTextEBookListByCourseSeriesIds(List<Long> courseSeriesIds) {
        if (courseSeriesIds == null || courseSeriesIds.isEmpty()) {
            return Flux.empty();
        }
        String sql = """
                SELECT tb.id as textEBookId,
                    tb.course_id as courseId,
                    tb.textebook_unique_code as textEBookUniqueCode,
                    tb.textebook_image_url as textEBookImageUrl,
                    tb.textebook_title as textEBookTitle,
                    tb.textebook_description as textEBookDescription,
                    tb.textebook_table_of_contents as textEBookTableOfContents,
                    tb.textebook_author as textEBookAuthor,
                    tb.textebook_publisher as textEBookPublisher,
                    tb.textebook_preview_download_url as textEBookPreviewDownloadUrl,
                    tb.textebook_page as textEBookPage,
                    tb.subject_category as subjectCategory,
                    tb.textebook_status as textEBookStatus,
                    tb.textebook_published_at as textEBookPublishedAt
                FROM text_e_books tb
                INNER JOIN courses c ON c.id = tb.course_id
                WHERE c.course_series_id IN (:courseSeriesIds);
                """;

        return databaseClient.sql(sql)
                .bind("courseSeriesIds", courseSeriesIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new TextEBookResult(
                        (Long) row.get("textEBookId"),
                        (Long) row.get("courseId"),
                        0L,
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
                        TextEBookStatus.valueOf((String) row.get("textEBookStatus")),
                        ((ZonedDateTime) row.get("textEBookPublishedAt")).toLocalDateTime()
                )));
    }

    @Override
    public Mono<TextEBookCountResult> getTextEBookCountResult() {
        String sql = """
                SELECT tb.textebook_status as textebookStatus,
                    COUNT(*) as statusCount
                FROM text_e_books tb
                GROUP BY tb.textebook_status;
                """;
        return databaseClient.sql(sql)
                .fetch()
                .all()
                .collectList()
                .map(rowList -> {
                    Long operatorCnt = 0L;
                    Long notOperatorCnt = 0L;
                    for (Map<String, Object> map: rowList) {
                        if (TextEBookStatus.valueOf((String) map.get("textebookStatus")).equals(TextEBookStatus.OPERATOR)) {
                            operatorCnt = (Long) map.get("statusCount");
                        } else if (TextEBookStatus.valueOf((String) map.get("textebookStatus")).equals(TextEBookStatus.NOT_OPERATOR)) {
                            notOperatorCnt = (Long) map.get("statusCount");
                        }
                    }
                    return new TextEBookCountResult(operatorCnt, notOperatorCnt);
                });
    }
}
