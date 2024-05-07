package org.lucycato.productservice.domain;

import lombok.*;
import org.lucycato.productservice.domain.enums.ProductGenre;
import org.lucycato.productservice.domain.enums.Subject;
import org.lucycato.productservice.domain.enums.TextEBookStatus;

import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextEBook {
    private Long testEBookId;

    private Long teacherId;

    private Long lectureId;

    private String uniqueCode;

    private Subject subject;

    private ProductGenre genre;

    private TextEBookStatus status;

    private String imageUrl;

    private String title;

    private String author;

    private String publisher;

    private String previewDownloadUrl;

    private String fullDownLoadUrl;

    private Integer page;

    private Integer sellingPrice;

    private String contents;

    private String description;

    private LocalDateTime publishedAt;
}
