package com.rsupport.notice.web;

import com.rsupport.notice.service.NoticeService;
import com.rsupport.notice.web.dto.NoticeResponseDto;
import com.rsupport.notice.web.dto.NoticeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Long save(@RequestBody NoticeResponseDto noticeSaveRequestDto){
        return noticeService.save(noticeSaveRequestDto);
    }

    @PutMapping("/api/notice/{id}")
    public Long update(@PathVariable Long id, @RequestBody NoticeUpdateDto noticeUpdateDto){
        return noticeService.update(id, noticeUpdateDto);
    }

}
