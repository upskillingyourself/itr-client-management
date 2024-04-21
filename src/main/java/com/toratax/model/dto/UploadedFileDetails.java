package com.toratax.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UploadedFileDetails {
    private int type;
    private String filePath;
    private String filename;
    private String originalFilename;

}
