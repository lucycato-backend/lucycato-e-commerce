package org.lucycato.productqueryservice.application.port.out;

import org.lucycato.productqueryservice.application.port.out.result.LectureResult;
import reactor.core.publisher.Flux;

import java.util.List;

public interface LecturePort {

    Flux<LectureResult> getLectureListByCourseIds(List<Long> courseIds);
}
