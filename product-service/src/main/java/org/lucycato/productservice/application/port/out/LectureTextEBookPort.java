package org.lucycato.productservice.application.port.out;

import org.lucycato.productservice.application.port.out.result.LectureTextEBookResult;
import org.lucycato.productservice.domain.enums.SubjectCategory;
import org.lucycato.productservice.domain.enums.TeachingGenre;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LectureTextEBookPort {
    Mono<LectureTextEBookResult> registerLectureTextEBook(
            Long lectureId,
            String eBookUniqueCode,
            String title,
            String description,
            String tableOfContents,
            String author,
            String publisher,
            Integer page,
            SubjectCategory subjectCategory,
            TeachingGenre teachingGenre

    );

    Mono<LectureTextEBookResult> modifyLectureTextEBook(
            Long lectureTextEBookId,
            List<String> lectureTextEBookImageUrls,
            String previewTextEBookPDFUrl,
            String fullTextEBookPDFUrl
    );
}
