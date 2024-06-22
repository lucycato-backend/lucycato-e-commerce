package org.lucycato.boardcommandservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "teacher_qna")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class TeacherQnAJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Long teacherId;

    private Long lectureId;

    private String title;

    private String content;

    private String answer;

    public TeacherQnAJpaEntity(Long studentId, Long teacherId, Long lectureId, String title, String content) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.lectureId = lectureId;
        this.title = title;
        this.content = content;
    }
}
