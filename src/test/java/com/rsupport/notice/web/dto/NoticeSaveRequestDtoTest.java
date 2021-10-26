package com.rsupport.notice.web.dto;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class NoticeSaveRequestDtoTest {
    @Test
    public void 롬복기능_테스트(){
        // given
        String title = "테스트 공지사항";
        String content = "테스트 내용";
        LocalDateTime startDate = LocalDateTime.parse("2021-10-27T04:44:39.893528");
        LocalDateTime endDate = LocalDateTime.parse("2021-10-27T04:44:39.893528");
        Long fileId = 1L;

        // when
        NoticeResponseDto dto = new NoticeResponseDto(title, content, startDate, endDate, fileId);

        // then
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getContent()).isEqualTo(content);
        assertThat(dto.getStartDate()).isEqualTo(startDate);
        assertThat(dto.getEndDate()).isEqualTo(endDate);
        assertThat(dto.getFileId()).isEqualTo(fileId);
    }
}