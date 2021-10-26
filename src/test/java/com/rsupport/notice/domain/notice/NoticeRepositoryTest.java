package com.rsupport.notice.domain.notice;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeRepositoryTest {
    @Autowired
    NoticeRepository noticeRepository;

    @After
    public void cleanup(){
        noticeRepository.deleteAll();
    }

    @Test
    public void 공지사항저장_불러오기(){
        // given
        String title = "테스트 공지사항";
        String content = "테스트 본문";

        noticeRepository.save(Notice.builder()
                .title(title)
                .content(content)
                .fileId(1L)
                .build());

        // when
        List<Notice> noticeList = noticeRepository.findAll();

        // then
        Notice notice = noticeList.get(0);
        assertThat(notice.getTitle()).isEqualTo(title);
        assertThat(notice.getContent()).isEqualTo(content);
        assertThat(notice.getFileId()).isEqualTo(1L);
    }
}