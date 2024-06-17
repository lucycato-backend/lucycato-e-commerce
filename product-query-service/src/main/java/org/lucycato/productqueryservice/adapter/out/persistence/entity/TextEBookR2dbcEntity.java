package org.lucycato.productqueryservice.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productqueryservice.domain.enums.SubjectCategory;
import org.lucycato.productqueryservice.domain.enums.TeachingGenre;
import org.lucycato.productqueryservice.domain.enums.TextEBookStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("text_e_book")
@Getter
@Setter
@NoArgsConstructor
public class TextEBookR2dbcEntity {
    @Id
    private Long id;

    private Long courseId;

    private String eBookUniqueCode;

    private String imageUrl;

    private String title;

    private String description;

    private String tableOfContents;

    private String author;

    private String publisher;

    private String previewDownloadUrl;

    private String fullDownLoadUrl;

    private Integer page;

    private SubjectCategory subjectCategory;

    private TeachingGenre genre;

    private TextEBookStatus status;

    private LocalDateTime publishedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
