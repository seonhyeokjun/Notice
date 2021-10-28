package com.rsupport.notice.web;

import com.rsupport.notice.domain.notice.Notice;
import com.rsupport.notice.domain.notice.NoticeRepository;
import com.rsupport.notice.web.dto.NoticeResponseDto;
import com.rsupport.notice.web.dto.NoticeUpdateDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoticeApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private NoticeRepository noticeRepository;

    @After
    public void tearDown(){
        noticeRepository.deleteAll();
    }

    @Test
    public void Notice_등록된다(){
        // given
        String title = "title";
        String content = "content";
        NoticeResponseDto responseDto = NoticeResponseDto.builder()
                .title(title)
                .content(content)
                .build();

        String url = "http://localhost:" + port + "/api/notice/save";

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, responseDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Notice> all = noticeRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Notice_수정된다(){
        // given
        Notice notice = noticeRepository.save(Notice.builder()
                .title("title")
                .content("content")
                .build());

        Long updateId = notice.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        NoticeUpdateDto noticeUpdateDto = NoticeUpdateDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost" + port + "/api/notice/" + updateId;
        HttpEntity<NoticeUpdateDto> requestEntity = new HttpEntity<>(noticeUpdateDto);

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Notice> all = noticeRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}