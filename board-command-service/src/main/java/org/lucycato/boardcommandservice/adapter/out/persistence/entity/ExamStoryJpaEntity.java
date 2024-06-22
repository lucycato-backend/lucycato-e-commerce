package org.lucycato.boardcommandservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "exam_story")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class ExamStoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Long teacherId;

    private String title;

    private String content;

    private String type;

    public ExamStoryJpaEntity(Long studentId, Long teacherId, String title, String content, String type) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.title = title;
        this.content = content;
        this.type = type;
    }
}
