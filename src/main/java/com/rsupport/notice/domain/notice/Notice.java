package com.rsupport.notice.domain.notice;

import com.rsupport.notice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Notice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long fileId;

    @Builder
    public Notice(String title, String content, LocalDateTime startDate, LocalDateTime endDate, Long fileId){
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fileId = fileId;
    }

    public void update(String title, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.title= title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
