package org.lucycato.productcommandservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;
import org.lucycato.productcommandservice.domain.enums.TextEBookStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "text_e_books")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TextEBookJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseJpaEntity courseJpaEntity;

    @Column(length = 200, nullable = false)
    private String textEBookUniqueCode;

    @Column(length = 200, nullable = false)
    private String textEBookImageUrl;

    @Column(length = 100, nullable = false)
    private String textEBookTitle;

    @Column(length = 512, nullable = false)
    private String textEBookDescription;

    @Column(length = 512, nullable = false)
    private String textEBookTableOfContents;

    @Column(length = 50, nullable = false)
    private String textEBookAuthor;

    @Column(length = 50, nullable = false)
    private String textEBookPublisher;

    @Column(length = 200, nullable = false)
    private String textEBookPreviewDownloadUrl;

    @Column(length = 200, nullable = false)
    private String textEBookFullDownloadUrl;

    @Column(nullable = false)
    private Integer textEBookPage;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private SubjectCategory subjectCategory;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private TextEBookStatus textEBookStatus;

    private LocalDateTime textEBookPublishedAt;

    @CreatedDate
    private LocalDateTime textEBookCreatedAt;

    @LastModifiedDate
    private LocalDateTime textEBookModifiedAt;

    public static TextEBookJpaEntity create(
            CourseJpaEntity courseJpaEntity,
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
        TextEBookJpaEntity textEBookJpaEntity = TextEBookJpaEntity.builder()
                .courseJpaEntity(courseJpaEntity)
                .textEBookUniqueCode(textEBookUniqueCode)
                .textEBookImageUrl(textEBookImageUrl)
                .textEBookTitle(textEBookTitle)
                .textEBookDescription(textEBookDescription)
                .textEBookTableOfContents(textEBookTableOfContents)
                .textEBookAuthor(textEBookAuthor)
                .textEBookPublisher(textEBookPublisher)
                .textEBookPreviewDownloadUrl(textEBookPreviewDownloadUrl)
                .textEBookFullDownloadUrl(textEBookFullDownloadUrl)
                .textEBookPage(textEBookPage)
                .subjectCategory(subjectCategory)
                .textEBookStatus(textEBookStatus)
                .textEBookPublishedAt(textEBookPublishedAt)
                .build();
        courseJpaEntity.addTextEBook(textEBookJpaEntity);
        return textEBookJpaEntity;
    }

    public static TextEBookJpaEntity create(
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
        return TextEBookJpaEntity.builder()
                .courseJpaEntity(CourseJpaEntity.builder().id(courseId).build())
                .textEBookUniqueCode(textEBookUniqueCode)
                .textEBookImageUrl(textEBookImageUrl)
                .textEBookTitle(textEBookTitle)
                .textEBookDescription(textEBookDescription)
                .textEBookTableOfContents(textEBookTableOfContents)
                .textEBookAuthor(textEBookAuthor)
                .textEBookPublisher(textEBookPublisher)
                .textEBookPreviewDownloadUrl(textEBookPreviewDownloadUrl)
                .textEBookFullDownloadUrl(textEBookFullDownloadUrl)
                .textEBookPage(textEBookPage)
                .subjectCategory(subjectCategory)
                .textEBookStatus(textEBookStatus)
                .textEBookPublishedAt(textEBookPublishedAt)
                .build();
    }
}
