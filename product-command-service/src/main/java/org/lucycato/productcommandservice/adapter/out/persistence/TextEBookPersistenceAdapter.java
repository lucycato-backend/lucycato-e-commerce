package org.lucycato.productcommandservice.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.CourseJpaEntity;
import org.lucycato.productcommandservice.adapter.out.persistence.entity.TextEBookJpaEntity;
import org.lucycato.productcommandservice.adapter.out.persistence.repository.TextEBookJpaRepository;
import org.lucycato.productcommandservice.application.port.out.TextEBookPort;
import org.lucycato.productcommandservice.application.port.out.result.TextEBookDetailResult;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.lucycato.productcommandservice.domain.enums.TextEBookStatus;

import java.time.LocalDateTime;

@PersistenceAdapter
@RequiredArgsConstructor
public class TextEBookPersistenceAdapter implements TextEBookPort {
    private final TextEBookJpaRepository textEBookJpaRepository;

    @Override
    public TextEBookDetailResult registerTextEBook(
            Long courseId,
            String textEBookUniqueCode,
            String textEBookImageUrl,
            String textEBookTitle,
            String textEBookDescription,
            String textEBookTableOfContents,
            String textEBookAuthor,
            String textEBookPublisher,
            String textEBookPreviewDownloadUrl,
            String textEBookFullDownloadUrl,
            Integer textEBookPage,
            SubjectCategory subjectCategory,
            TextEBookStatus textEBookStatus,
            LocalDateTime textEBookPublishedAt
    ) {
        return TextEBookDetailResult.from(textEBookJpaRepository.save(TextEBookJpaEntity.create(
                courseId,
                textEBookUniqueCode,
                textEBookImageUrl,
                textEBookTitle,
                textEBookDescription,
                textEBookTableOfContents,
                textEBookAuthor,
                textEBookPublisher,
                textEBookPreviewDownloadUrl,
                textEBookFullDownloadUrl,
                textEBookPage,
                subjectCategory,
                textEBookStatus,
                textEBookPublishedAt
        )));
    }

    @Override
    public TextEBookDetailResult modifyTextEBook(
            Long textEBookId,
            Long courseId,
            String textEBookUniqueCode,
            String textEBookImageUrl,
            String textEBookTitle,
            String textEBookDescription,
            String textEBookTableOfContents,
            String textEBookAuthor,
            String textEBookPublisher,
            String textEBookPreviewDownloadUrl,
            String textEBookFullDownloadUrl,
            Integer textEBookPage,
            SubjectCategory subjectCategory,
            LocalDateTime textEBookPublishedAt
    ) {
        TextEBookJpaEntity textEBookJpaEntity = textEBookJpaRepository.findById(textEBookId).orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
        CourseJpaEntity courseJpaEntity = CourseJpaEntity.builder()
                .id(courseId)
                .build();

        textEBookJpaEntity.setCourseJpaEntity(courseJpaEntity);
        textEBookJpaEntity.setTextEBookUniqueCode(textEBookUniqueCode);
        textEBookJpaEntity.setTextEBookImageUrl(textEBookImageUrl);
        textEBookJpaEntity.setTextEBookTitle(textEBookTitle);
        textEBookJpaEntity.setTextEBookDescription(textEBookDescription);
        textEBookJpaEntity.setTextEBookTableOfContents(textEBookTableOfContents);
        textEBookJpaEntity.setTextEBookAuthor(textEBookAuthor);
        textEBookJpaEntity.setTextEBookPublisher(textEBookPublisher);
        textEBookJpaEntity.setTextEBookPreviewDownloadUrl(textEBookPreviewDownloadUrl);
        textEBookJpaEntity.setTextEBookFullDownloadUrl(textEBookFullDownloadUrl);
        textEBookJpaEntity.setTextEBookPage(textEBookPage);
        textEBookJpaEntity.setSubjectCategory(subjectCategory);
        textEBookJpaEntity.setTextEBookPublishedAt(textEBookPublishedAt);

        return TextEBookDetailResult.from(textEBookJpaRepository.save(textEBookJpaEntity));
    }

    @Override
    public void removeTextEBook(Long textEBook) {
        textEBookJpaRepository.deleteById(textEBook);
    }
}
