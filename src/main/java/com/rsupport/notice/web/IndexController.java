package com.rsupport.notice.web;

import com.rsupport.notice.service.NoticeService;
import com.rsupport.notice.web.dto.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final NoticeService noticeService;

    /**
     * 공지사항 조회 화면
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("notice", noticeService.findAllDesc());

        return "index";
    }

    /**
     * 공지사항 생성 화면으로 이동
     * @return
     */
    @GetMapping("/notice/save")
    public String noticeSave(){
        return "notice-save";
    }

    @GetMapping("/notice/update/{id}")
    public String noticeUpdate(@PathVariable Long id, Model model){
        NoticeResponseDto noticeResponseDto = noticeService.findById(id);
        model.addAttribute("notice", noticeResponseDto);

        return "notice-update";
    }
}
