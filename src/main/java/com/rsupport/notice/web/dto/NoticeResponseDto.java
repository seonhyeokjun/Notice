package com.rsupport.notice.web.dto;

import com.rsupport.notice.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int hit;
    private LocalDateTime createdDate;

    @Builder
    public NoticeResponseDto(String title, String content, LocalDateTime startDate, LocalDateTime endDate, int hit){
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hit = hit;
    }

    public Notice toEntity(){
        return Notice.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .hit(hit)
                .build();
    }

    public NoticeResponseDto(Notice notice){
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.startDate = notice.getStartDate();
        this.endDate = notice.getEndDate();
        this.hit = notice.getHit();
        this.createdDate = notice.getCreatedDate();
    }
}
