package com.rsupport.notice.web.dto;

import com.rsupport.notice.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private Long fileId;

    @Builder
    public NoticeSaveRequestDto(String title, String content, String author, Long fileId){
        this.title = title;
        this.content = content;
        this.author = author;
        this.fileId = fileId;
    }

    public Notice toEntity(){
        return Notice.builder()
                .title(title)
                .content(content)
                .author(author)
                .fileId(fileId)
                .build();
    }
}
