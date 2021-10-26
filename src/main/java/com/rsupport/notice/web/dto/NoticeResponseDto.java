package com.rsupport.notice.web.dto;

import com.rsupport.notice.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long fileId;

    @Builder
    public NoticeResponseDto(String title, String content, LocalDateTime startDate, LocalDateTime endDate, Long fileId){
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fileId = fileId;
    }

    public Notice toEntity(){
        return Notice.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .fileId(fileId)
                .build();
    }

    public NoticeResponseDto(Notice notice){
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.startDate = notice.getStartDate();
        this.endDate = notice.getEndDate();
        this.fileId = notice.getFileId();
    }
}
