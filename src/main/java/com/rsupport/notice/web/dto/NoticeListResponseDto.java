package com.rsupport.notice.web.dto;

import com.rsupport.notice.domain.notice.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeListResponseDto {
    private Long id;
    private String title;
    private LocalDateTime modifiedDate;

    public NoticeListResponseDto(Notice notice){
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.modifiedDate = notice.getModifiedDate();
    }
}
