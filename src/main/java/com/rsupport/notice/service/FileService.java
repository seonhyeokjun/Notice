package com.rsupport.notice.service;

import com.rsupport.notice.domain.notice.File;
import com.rsupport.notice.domain.notice.FileRepository;
import com.rsupport.notice.web.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    @Transactional
    public Long saveFile(FileDto fileDto){
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public FileDto getFile(Long id){
        File file = fileRepository.findById(id).get();

        return FileDto.builder()
                .id(id)
                .realFilename(file.getRealFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
    }
}
