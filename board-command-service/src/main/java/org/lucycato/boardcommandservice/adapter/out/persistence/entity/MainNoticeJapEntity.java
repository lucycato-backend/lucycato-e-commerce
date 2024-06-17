package org.lucycato.boardcommandservice.adapter.out.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "main_notice")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class MainNoticeJapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    private String content;

    private String type;

    public MainNoticeJapEntity(Long userId, String title, String content, String type) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.type = type;
    }
}
