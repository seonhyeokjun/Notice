package com.rsupport.notice.web;

import com.rsupport.notice.service.NoticeService;
import com.rsupport.notice.web.dto.NoticeResponseDto;
import com.rsupport.notice.web.dto.NoticeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class NoticeApiController {
    private final NoticeService noticeService;

    /**
     * 공지사항 저장
     * @param noticeResponseDto
     * @return
     */
    @PostMapping("/api/notice/save")
    public Long save(@RequestPart("key") NoticeResponseDto noticeResponseDto,
                     @RequestPart("files") List<MultipartFile> files) throws Exception {
        return noticeService.save(noticeResponseDto, files);
    }

    /**
     * 공지사항 수정
     * @param id
     * @param noticeUpdateDto
     * @return
     */
    @PutMapping("/api/notice/{id}")
    public Long update(@PathVariable Long id, @RequestBody NoticeUpdateDto noticeUpdateDto){
        return noticeService.update(id, noticeUpdateDto);
    }

    /**
     * 공지사항 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/api/notice/{id}")
    public Long delete(@PathVariable Long id){
        noticeService.delete(id);

        return id;
    }
}
