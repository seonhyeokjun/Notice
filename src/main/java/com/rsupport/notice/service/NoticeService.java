package com.rsupport.notice.service;

import com.rsupport.notice.domain.notice.FileLoad;
import com.rsupport.notice.domain.notice.Notice;
import com.rsupport.notice.domain.notice.NoticeRepository;
import com.rsupport.notice.web.dto.NoticeListResponseDto;
import com.rsupport.notice.web.dto.NoticeResponseDto;
import com.rsupport.notice.web.dto.NoticeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    /**
     * 공지사항 및 다중 첨부파일 저장
     * @param noticeSaveRequestDto
     * @param files
     * @return
     * @throws Exception
     */
    @Transactional
    public Long save(NoticeResponseDto noticeSaveRequestDto, List<MultipartFile> files) throws Exception {
        Notice notice = new Notice(noticeSaveRequestDto.getTitle(), noticeSaveRequestDto.getContent(),
                noticeSaveRequestDto.getStartDate(), noticeSaveRequestDto.getEndDate(), noticeSaveRequestDto.getHit());
        for (MultipartFile file : files){
            if (file.getOriginalFilename().equals("")) continue;
            String realFilename = file.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String filename = uuid + "_" + file.getOriginalFilename();
            String savePath = System.getProperty("user.dir") + "/src/main/resources/static/files";
            String filePath = savePath + "/" + filename;
            file.transferTo(new File(filePath));
            FileLoad fileLoad = new FileLoad(realFilename, filename, filePath);
            notice.addFile(fileLoad);
        }

        return noticeRepository.save(notice).getId();
    }

    /**
     * 공지사항 전체 조회
     * @return
     */
    @Transactional(readOnly = true)
    public List<NoticeListResponseDto> findAllDesc(){
        return noticeRepository.findAllDesc().stream().map(NoticeListResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 공지사항 상세
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public NoticeResponseDto findById(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new NoticeResponseDto(notice);
    }

    /**
     * 공지사항 수정
     * @param id
     * @param noticeUpdateDto
     * @return
     */
    @Transactional
    public Long update(Long id, NoticeUpdateDto noticeUpdateDto) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        notice.update(noticeUpdateDto.getTitle(), noticeUpdateDto.getContent(), noticeUpdateDto.getStartDate(),
                noticeUpdateDto.getEndDate());

        return id;
    }

    /**
     * 조회수 증가
     * @param id
     */
    @Transactional
    public void updateHit(Long id){
        noticeRepository.updateHit(id);
    }

    /**
     * 공지사항 삭제
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        noticeRepository.delete(notice);
    }
}
