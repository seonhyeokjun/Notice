package com.rsupport.notice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeUpdateDto {
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Builder
    public NoticeUpdateDto(String title, String content, LocalDateTime startDate, LocalDateTime endDate){
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
