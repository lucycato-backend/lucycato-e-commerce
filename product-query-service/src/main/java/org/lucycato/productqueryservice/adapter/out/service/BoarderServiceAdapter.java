package org.lucycato.productqueryservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.productqueryservice.application.port.out.BoardPort;
import org.lucycato.productqueryservice.application.port.out.result.CheckedRecentTeacherNewsResult;
import org.lucycato.webflux.CommonWebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@ServiceAdapter
@RequiredArgsConstructor
public class BoarderServiceAdapter implements BoardPort {
    private final CommonWebClient commonWebClient;

    @Override
    public Flux<CheckedRecentTeacherNewsResult> checkTeacherNewsListByTeacherIds(List<Long> teacherIds) {
        return null;
    }

    @Override
    public Mono<Long> countTeacherNoticeCount() {
        return null;
    }

    @Override
    public Mono<Long> getCourseReviewCount() {
        return null;
    }
}
