package org.lucycato.productcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.lucycato.productcommandservice.domain.enums.TextEBookStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RegisterTextEBookRequest {
    private Long courseId;

    private String textEBookUniqueCode;

    private String textEBookTitle;

    private String textEBookDescription;

    private String textEBookTableOfContents;

    private String textEBookAuthor;

    private String textEBookPublisher;

    private Integer textEBookPage;

    private SubjectCategory subjectCategory;

    private LocalDateTime textEBookPublishedAt;
}
