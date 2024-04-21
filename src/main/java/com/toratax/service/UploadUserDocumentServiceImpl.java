package com.toratax.service;

import com.toratax.model.domain.*;
import com.toratax.model.dto.UploadDocumentInformation;
import com.toratax.repository.DocumentTypeRepository;
import com.toratax.repository.PermanentDataRepository;
import com.toratax.repository.PermanentDocumentsRepository;
import com.toratax.repository.UserYearlyInformationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.toratax.util.RemoteFileToLocalFile.multipartFileToFile;
import static com.toratax.util.ServiceConstants.PERMANENT_REFERENCE_TABLE;

@Service
public class UploadUserDocumentServiceImpl implements UploadUserDocumentService {
    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    @Autowired
    private UserYearlyInformationRepository userYearlyInformationRepository;
    @Autowired
    private PermanentDataRepository permanentDataRepository;
    @Autowired
    private PermanentDocumentsRepository permanentDocumentsRepository;

    private Map<Integer, DocumentType> documentTypeDetails = new HashMap<>();

    @PostConstruct
    private void init() {
        for (DocumentType documentType : documentTypeRepository.findAll()) {
            documentTypeDetails.put(documentType.getId(), documentType);
        }
    }

    @Override
    @Transactional
    public void uploadDocument(UploadDocumentInformation documentInformation, MultipartFile multipartFile) {
        DocumentType documentType = documentTypeDetails.get(documentInformation.getTypeId());
        documentInformation.setFilename(multipartFile.getOriginalFilename());
        String referenceTable= documentType.getReferenceTable();
        if (PERMANENT_REFERENCE_TABLE.equalsIgnoreCase(referenceTable)){
            savePermanentData(documentInformation, multipartFile);
        }else{
            saveYearlyData(documentInformation, multipartFile);
        }

    }

    private void saveYearlyData(UploadDocumentInformation uploadDocumentInformation,  MultipartFile multipartFile){
        UserYearlyInformation userYearlyInfo=userYearlyInformationRepository.findByUserId(uploadDocumentInformation.getUsername());
        Set<YearlyDocument> yearlyDocuments= new HashSet<>();
        YearlyDocument yearlyDocument= new YearlyDocument();
        yearlyDocument.setDocumentName(uploadDocumentInformation.getFilename());
        String filepath=userYearlyInfo.getYearlyFolderPath().concat(File.separator).concat(uploadDocumentInformation.getFilename());
        try {
            multipartFileToFile(multipartFile, filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        yearlyDocument.setDocumentPath(filepath);
        yearlyDocument.setDocumentTypeId(uploadDocumentInformation.getTypeId());
        yearlyDocument.setUploadedDate(LocalDate.now());
        yearlyDocument.setYearlyInformation(userYearlyInfo);
        yearlyDocuments.add(yearlyDocument);
        userYearlyInfo.setYearlyDocuments(yearlyDocuments);
        userYearlyInformationRepository.save(userYearlyInfo);

    }

    private void savePermanentData(UploadDocumentInformation documentInformation,  MultipartFile multipartFile){
        UserPermanentData userPermanentData= permanentDataRepository.findByUserId(documentInformation.getUsername());
        Set<PermanentDocument> permanentDocuments= new HashSet<>();
        String documentPath=userPermanentData.getFolderPath().concat(File.separator).concat(documentInformation.getFilename());
        PermanentDocument permanentDocument=permanentDocumentsRepository.findByUserPermanentDataAndDocumentTypeId(userPermanentData, documentInformation.getTypeId());;

        if(permanentDocument==null){
            permanentDocument=new PermanentDocument();
        }
        permanentDocument.setDocumentName(documentInformation.getFilename());
        permanentDocument.setUploadedDate(LocalDate.now());
        try {
            multipartFileToFile(multipartFile, documentPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        permanentDocument.setDocumentPath(documentPath);
        permanentDocument.setDocumentTypeId(documentInformation.getTypeId());
        permanentDocument.setUserPermanentData(userPermanentData);
        permanentDocuments.add(permanentDocument);
        userPermanentData.setPermanentDocuments(permanentDocuments);
        permanentDataRepository.save(userPermanentData);
    }
}
