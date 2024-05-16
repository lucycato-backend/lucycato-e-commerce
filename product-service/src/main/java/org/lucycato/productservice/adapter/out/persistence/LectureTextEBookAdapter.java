package org.lucycato.productservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.productservice.adapter.out.persistence.entity.LectureTextEBookR2dbcEntity;
import org.lucycato.productservice.adapter.out.persistence.repository.LectureTextEBookReactiveRepository;
import org.lucycato.productservice.application.port.out.LectureTextEBookPort;
import org.lucycato.productservice.application.port.out.result.LectureTextEBookResult;
import org.lucycato.productservice.domain.enums.SubjectCategory;
import org.lucycato.productservice.domain.enums.TeachingGenre;
import org.lucycato.productservice.domain.enums.TextEBookStatus;
import reactor.core.publisher.Mono;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LectureTextEBookAdapter implements LectureTextEBookPort {
    private final LectureTextEBookReactiveRepository lectureTextEBookReactiveRepository;
    @Override
    public Mono<LectureTextEBookResult> registerLectureTextEBook(Long lectureId, String eBookUniqueCode, String title, String description, String tableOfContents, String author, String publisher, Integer page, SubjectCategory subjectCategory, TeachingGenre teachingGenre) {
        return lectureTextEBookReactiveRepository.save(
                new LectureTextEBookR2dbcEntity(
                        lectureId,
                        eBookUniqueCode,
                        title,
                        description,
                        tableOfContents,
                        author,
                        publisher,
                        page,
                        subjectCategory,
                        teachingGenre,
                        TextEBookStatus.NOT_OPERATOR
                )
        ).map(LectureTextEBookResult::from);
    }

    @Override
    public Mono<LectureTextEBookResult> modifyLectureTextEBook(Long lectureTextEBookId, List<String> lectureTextEBookImageUrls, String previewTextEBookPDFUrl, String fullTextEBookPDFUrl) {
        return null;
    }
}
