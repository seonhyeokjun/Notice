package com.rsupport.notice.web;

import com.rsupport.notice.service.NoticeService;
import com.rsupport.notice.web.dto.NoticeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoticeApiController {
    private final NoticeService noticeService;

    /**
     * 공지사항 저장
     * @param noticeSaveRequestDto
     * @return
     */
    @PostMapping("/api/notice/save")
    public Long save(@RequestBody NoticeSaveRequestDto noticeSaveRequestDto){
        return noticeService.save(noticeSaveRequestDto);
    }
}
