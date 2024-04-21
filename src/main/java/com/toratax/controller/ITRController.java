package com.toratax.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toratax.model.dto.ItrDetails;
import com.toratax.model.dto.ItrRequest;
import com.toratax.model.dto.TorataxItrDetails;
import com.toratax.model.dto.UploadDocumentInformation;
import com.toratax.model.mapper.DtoToDomainModel;
import com.toratax.service.ITRService;
import com.toratax.service.UploadUserDocumentService;
import com.toratax.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ITRController {
    @Autowired
    private ITRService itrService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UploadUserDocumentService uploadUserDocumentService;

    @PostMapping("/toratax/rest/v1.0/itrrequest")
    public ResponseEntity<TorataxItrDetails> requestITR(@RequestBody(required = true) ItrRequest itrData,Authentication authentication) throws JsonProcessingException {
        itrData.setUserId(authentication.getName());
        return new ResponseEntity<>(itrService.requestITR(itrData), HttpStatus.CREATED);
    }

    @PostMapping("/toratax/rest/v1.0/fileupload")
    public ResponseEntity<String> uploadDocument(@RequestPart("document") MultipartFile multipartFile,
                                            @RequestPart("info") String uploadedFileInfo, Authentication authentication) throws JsonProcessingException {
        UploadDocumentInformation fileInfo = objectMapper.readValue(uploadedFileInfo, UploadDocumentInformation.class);
        fileInfo.setUsername(authentication.getName());
        uploadUserDocumentService.uploadDocument(fileInfo, multipartFile);
        return new ResponseEntity<>(ServiceConstants.SUCCESS_MESSAGE, HttpStatus.CREATED);
    }


}
