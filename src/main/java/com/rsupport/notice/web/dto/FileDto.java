package com.rsupport.notice.web.dto;

import com.rsupport.notice.domain.notice.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.FileDescriptor;

@Getter
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String realFilename;
    private String filename;
    private String filePath;

    public File toEntity(){
        return File.builder()
                .id(id)
                .realFilename(realFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
    }

    @Builder
    public FileDto(Long id, String realFilename, String filename, String filePath){
        this.id = id;
        this.realFilename = realFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
