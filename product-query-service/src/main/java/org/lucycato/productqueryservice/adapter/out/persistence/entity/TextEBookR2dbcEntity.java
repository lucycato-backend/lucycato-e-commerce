package org.lucycato.productqueryservice.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("text_e_books")
@Getter
@Setter
@NoArgsConstructor
public class TextEBookR2dbcEntity {
    @Id
    private Long id;

    private Long courseId;

    private String textEBookUniqueCode;

    private String textEBookImageUrl;

    private String textEBookTitle;

    private String textEBookDescription;

    private String textEBookTableOfContents;

    private String textEBookAuthor;

    private String textEBookPublisher;

    private String textEBookPreviewDownloadUrl;

    private String textEBookFullDownLoadUrl;

    private Integer textEBookPage;

    private String subjectCategory;

    private String textEBookGenre;

    private String textEBookStatus;

    private LocalDateTime textEBookPublishedAt;

    @CreatedDate
    private LocalDateTime textEBookCreatedAt;

    @LastModifiedDate
    private LocalDateTime textEBookModifiedAt;
}
