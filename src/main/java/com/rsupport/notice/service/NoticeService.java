package com.rsupport.notice.service;

import com.rsupport.notice.domain.notice.NoticeRepository;
import com.rsupport.notice.web.dto.NoticeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeSaveRequestDto noticeSaveRequestDto){
        return noticeRepository.save(noticeSaveRequestDto.toEntity()).getId();
    }
}
