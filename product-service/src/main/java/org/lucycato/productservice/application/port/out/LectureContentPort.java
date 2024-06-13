package org.lucycato.productservice.application.port.out;

import org.lucycato.productservice.application.port.out.result.LectureContentResult;
import org.lucycato.productservice.domain.enums.LectureContentCategory;
import reactor.core.publisher.Mono;

public interface LectureContentPort {

    Mono<LectureContentResult> registerLectureContent(
            Long lectureId,
            String lectureContentTitle,
            LectureContentCategory lectureContentCategory
    );

    Mono<LectureContentResult> modifyLectureContent(
            Long lectureContentId,
            String lectureContentThumbnailImageUrl,
            String lectureContentVideoUrl
    );
}
