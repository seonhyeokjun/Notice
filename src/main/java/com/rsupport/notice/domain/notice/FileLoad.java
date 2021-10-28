package com.rsupport.notice.domain.notice;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileLoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notice_id")
    private Notice notice;

    @Column(nullable = false)
    private String realFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public FileLoad(String realFilename, String filename, String filePath){
        this.realFilename = realFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

    public void setNotice(Notice notice){
        this.notice = notice;
        if (!notice.getFiles().contains(this)){
            notice.getFiles().add(this);
        }
    }
}
