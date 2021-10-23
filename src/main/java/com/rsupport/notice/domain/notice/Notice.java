package com.rsupport.notice.domain.notice;

import com.rsupport.notice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(length = 20, nullable = false)
    private String author;

    private Long fileId;

    @Builder
    public Notice(String title, String author, String content, Long fileId){
        this.title = title;
        this.author = author;
        this.content = content;
        this.fileId = fileId;
    }
}
