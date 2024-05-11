package org.lucycato.productservice.application.port.in;

import org.lucycato.productservice.application.port.in.command.ByIdCommand;
import org.lucycato.productservice.application.port.in.command.ByIdsCommand;
import org.lucycato.productservice.domain.LectureContent;
import org.lucycato.productservice.domain.TeacherNews;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherNewsQueryUseCase {
    Flux<TeacherNews.Record> getTeacherNewsList();

    Flux<TeacherNews.Record> getTeacherNewsListByTargetTeacherIds(ByIdsCommand command);

    Mono<TeacherNews> getTeacherNewsById(ByIdCommand command);
}
