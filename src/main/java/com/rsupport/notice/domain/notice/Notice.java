package com.rsupport.notice.domain.notice;

import com.rsupport.notice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int hit;

    @OneToMany(
            mappedBy = "notice",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<FileLoad> files = new ArrayList<>();

    @Builder
    public Notice(String title, String content, LocalDateTime startDate, LocalDateTime endDate, int hit){
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hit = hit;
    }

    public void update(String title, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.title= title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addFile(FileLoad file){
        this.files.add(file);
        if (file.getNotice() != this){
            file.setNotice(this);
        }
    }
}
