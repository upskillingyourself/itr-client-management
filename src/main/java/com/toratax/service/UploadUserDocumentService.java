package com.toratax.service;

import com.toratax.model.dto.UploadDocumentInformation;
import org.springframework.web.multipart.MultipartFile;

public interface UploadUserDocumentService {

    void uploadDocument(UploadDocumentInformation documentInformation, MultipartFile multipartFile);
}
