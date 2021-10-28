package com.rsupport.notice.web.dto;

import com.rsupport.notice.domain.notice.FileLoad;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileDto {
    private String realFilename;
    private String filename;
    private String filePath;

    @Builder
    public FileDto(String realFilename, String filename, String filePath) {
        this.realFilename = realFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

    public FileLoad toEntity(){
        return FileLoad.builder()
                .realFilename(realFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
    }

}
