package org.lucycato.productqueryservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productqueryservice.application.port.out.LecturePort;
import org.lucycato.productqueryservice.application.port.out.result.LectureResult;
import reactor.core.publisher.Flux;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements LecturePort {

    @Override
    public Flux<LectureResult> getLectureListByCourseIds(List<Long> courseIds) {
        return null;
    }
}
