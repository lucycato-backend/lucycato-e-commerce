package org.lucycato.productqueryservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productqueryservice.application.port.out.LecturePort;
import org.lucycato.productqueryservice.application.port.out.result.LectureResult;
import org.lucycato.productqueryservice.domain.enums.LectureCategory;
import org.lucycato.productqueryservice.domain.enums.LectureStatus;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements LecturePort {
    private final DatabaseClient databaseClient;

    @Override
    public Flux<LectureResult> getLectureListByCourseIds(List<Long> courseIds) {
        String sql = """
                SELECT id,
                    title,
                    description,
                    lecture_category,
                    video_url,
                    status
                FROM lecture
                WHERE course_id IN (:courseIds);
                """;

        return databaseClient.sql(sql)
                .bind("courseIds", courseIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new LectureResult(
                        (Long) row.get("id"),
                        (String) row.get("title"),
                        (String) row.get("description"),
                        LectureCategory.valueOf((String) row.get("lecture_category")),
                        (String) row.get("video_url"),
                        LectureStatus.valueOf((String) row.get("status"))
                )));
    }
}
