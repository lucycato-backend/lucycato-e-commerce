package org.lucycato.usercoursequeryservice.adapter.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.usercoursequeryservice.domain.enums.CourseGenre;
import org.lucycato.usercoursequeryservice.domain.enums.CourseStatus;
import org.lucycato.usercoursequeryservice.domain.enums.SubjectCategory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("user_course")
@Getter
@Setter
@NoArgsConstructor
public class UserCourseR2dbcEntity {
    @Id
    private Long id;

    private Long teacherId;

    private Long courseSeriesId;

    private String title;

    private String subTitle;

    private Integer price;

    private String imageUrl;

    private String description;

    private CourseGenre courseGenre;

    private SubjectCategory subjectCategory;

    private CourseStatus courseStatus;

    private LocalDateTime expiredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
