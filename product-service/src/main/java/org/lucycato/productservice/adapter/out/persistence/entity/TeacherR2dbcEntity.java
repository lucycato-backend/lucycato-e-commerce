package org.lucycato.productservice.adapter.out.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.productservice.domain.enums.TeachingGenre;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

//@Table("teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherR2dbcEntity {
    @Id
    private Long id;

    private Integer rank;

    private String name;

    private String slogan;

    private String profileDescription;

    private List<String> imageUrls;

    private String curriculumImageUrl;

    private String curriculumVideoUrl;

    private TeachingGenre genre;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public TeacherR2dbcEntity(String name, String slogan, String profileDescription, TeachingGenre genre) {
        this.name = name;
        this.slogan = slogan;
        this.profileDescription = profileDescription;
        this.genre = genre;
    }
}
