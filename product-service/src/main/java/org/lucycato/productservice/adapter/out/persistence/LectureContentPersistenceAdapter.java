package org.lucycato.productservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productservice.adapter.out.persistence.entity.LectureContentR2dbcEntity;
import org.lucycato.productservice.adapter.out.persistence.repository.LectureContentReactiveRepository;
import org.lucycato.productservice.application.port.out.LectureContentPort;
import org.lucycato.productservice.application.port.out.result.LectureContentResult;
import org.lucycato.productservice.domain.enums.LectureContentCategory;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class LectureContentPersistenceAdapter implements LectureContentPort {
    private final LectureContentReactiveRepository lectureContentReactiveRepository;

    @Override
    public Mono<LectureContentResult> registerLectureContent(
            Long lectureId,
            String lectureContentTitle,
            LectureContentCategory lectureContentCategory
    ) {
        return lectureContentReactiveRepository.save(new LectureContentR2dbcEntity(
                lectureId,
                lectureContentTitle,
                lectureContentCategory
                ))
                .map(LectureContentResult::from);
    }

    @Override
    public Mono<LectureContentResult> modifyLectureContent(Long lectureContentId, String lectureContentThumbnailImageUrl, String lectureContentVideoUrl) {
        return lectureContentReactiveRepository.findById(lectureContentId)
                .flatMap(entity -> {
                    entity.setThumbnailImageUrl(lectureContentThumbnailImageUrl);
                    entity.setVideoUrl(lectureContentVideoUrl);
                    return lectureContentReactiveRepository.save(entity);
                })
                .map(LectureContentResult::from);
    }
}
