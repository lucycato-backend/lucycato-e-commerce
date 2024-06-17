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
                SELECT l.id as lectureId,
                    l.lectureTitle as lectureTitle,
                    l.lectureDescription as lectureDescription,
                    l.lectureCategory as lectureCategory,
                    l.lectureVideoUrl as lectureVideoUrl,
                    l.lectureStatus as lectureStatus
                FROM lectures l
                WHERE l.course_id IN (:courseIds);
                """;

        return databaseClient.sql(sql)
                .bind("courseIds", courseIds)
                .fetch()
                .all()
                .flatMap(row -> Flux.just(new LectureResult(
                        (Long) row.get("lectureId"),
                        (String) row.get("lectureTitle"),
                        (String) row.get("lectureDescription"),
                        LectureCategory.valueOf((String) row.get("lectureCategory")),
                        (String) row.get("lectureVideoUrl"),
                        LectureStatus.valueOf((String) row.get("lectureStatus"))
                )));
    }
}
