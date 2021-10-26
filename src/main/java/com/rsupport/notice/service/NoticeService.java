package com.rsupport.notice.service;

import com.rsupport.notice.domain.notice.Notice;
import com.rsupport.notice.domain.notice.NoticeRepository;
import com.rsupport.notice.web.dto.NoticeListResponseDto;
import com.rsupport.notice.web.dto.NoticeResponseDto;
import com.rsupport.notice.web.dto.NoticeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeResponseDto noticeSaveRequestDto){
        return noticeRepository.save(noticeSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public NoticeResponseDto getPost(Long id){
        Notice notice = noticeRepository.findById(id).get();

        return NoticeResponseDto.builder()
                .title(notice.getTitle())
                .content(notice.getContent())
                .build();
    }

    @Transactional(readOnly = true)
    public List<NoticeListResponseDto> findAllDesc(){
        return noticeRepository.findAllDesc().stream().map(NoticeListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NoticeResponseDto findById(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new NoticeResponseDto(notice);
    }

    @Transactional
    public Long update(Long id, NoticeUpdateDto noticeUpdateDto) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        notice.update(noticeUpdateDto.getTitle(), noticeUpdateDto.getContent(), noticeUpdateDto.getStartDate(), noticeUpdateDto.getEndDate());

        return id;
    }
}
