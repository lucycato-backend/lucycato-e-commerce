package org.lucycato.productservice.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productservice.domain.enums.SubjectCategory;
import org.lucycato.productservice.domain.enums.TeachingGenre;
import org.lucycato.productservice.domain.enums.TextEBookStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table("lecture_text_e_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureTextEBookR2dbcEntity {
    @Id
    private Long id;

    private Long lectureId;

    private String eBookUniqueCode;

    private List<String> imageUrls;

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