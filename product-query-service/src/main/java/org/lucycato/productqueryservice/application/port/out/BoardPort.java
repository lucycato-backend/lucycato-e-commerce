package org.lucycato.productqueryservice.application.port.out;

import org.lucycato.productqueryservice.application.port.out.result.CheckedRecentTeacherNoticeResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BoardPort {

    Flux<CheckedRecentTeacherNoticeResult> checkTeacherNewsListByTeacherIds(List<Long> teacherIds);

    Mono<Long> countTeacherNoticeCount();

    Mono<Long> getCourseReviewCount();
}
