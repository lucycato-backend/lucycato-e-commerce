package org.lucycato.productqueryservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.productqueryservice.application.port.out.BoardPort;
import org.lucycato.productqueryservice.application.port.out.result.CheckedRecentTeacherNoticeResult;
import org.lucycato.webflux.CommonWebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@ServiceAdapter
@RequiredArgsConstructor
public class BoarderServiceAdapter implements BoardPort {
    private final CommonWebClient commonWebClient;

    @Override
    public Flux<CheckedRecentTeacherNoticeResult> checkTeacherNewsListByTeacherIds(List<Long> teacherIds) {
        return Flux.fromIterable(List.of());
    }

    @Override
    public Mono<Long> countTeacherNoticeCount() {
        return Mono.just(0L);
    }

    @Override
    public Mono<Long> getCourseReviewCount() {
        return Mono.just(0L);
    }
}
