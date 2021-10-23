package com.rsupport.notice.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class NoticeSaveRequestDtoTest {
    @Test
    public void 롬복기능_테스트(){
        // given
        String title = "테스트 공지사항";
        String content = "테스트 내용";
        String author = "선혁준";
        Long fileId = 1L;

        // when
        NoticeSaveRequestDto dto = new NoticeSaveRequestDto(title, content, author, fileId);

        // then
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getContent()).isEqualTo(content);
        assertThat(dto.getAuthor()).isEqualTo(author);
        assertThat(dto.getFileId()).isEqualTo(fileId);
    }
}