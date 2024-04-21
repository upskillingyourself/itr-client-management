package com.toratax.service;

import com.toratax.model.domain.UserPermanentData;
import com.toratax.model.domain.UserYearlyInformation;
import com.toratax.model.dto.ItrDetails;
import com.toratax.model.dto.ItrRequest;
import com.toratax.model.dto.TorataxItrDetails;
import com.toratax.repository.PermanentDataRepository;
import com.toratax.repository.UserYearlyInformationRepository;
import com.toratax.util.RemoteFileToLocalFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import static com.toratax.model.mapper.DtoToDomainModel.extractUserPermanentData;
import static com.toratax.model.mapper.DtoToDomainModel.extractUserYearlyInformation;


@Service
public class ITRServiceImpl implements ITRService {

    @Autowired
    private PermanentDataRepository userPermanentDataRepository;

    @Autowired
    private UserYearlyInformationRepository userYearlyInformationRepository;

    @Value("${root.folder.name}")
    private String rootFolderPath;

    @Override
    @Transactional
    public TorataxItrDetails requestITR(ItrRequest itrRequest) {

        ItrDetails permanentItrDetails = new ItrDetails();
        UserPermanentData userPermanentData = saveUserPermanentData(itrRequest);
        permanentItrDetails.setDataId(userPermanentData.getId());

        ItrDetails yearlyItrDetails = new ItrDetails();
        yearlyItrDetails.setDataId(saveUserYearlyInformation(itrRequest, userPermanentData.getFolderPath()).getId());

        TorataxItrDetails torataxItrDetails = new TorataxItrDetails();
        torataxItrDetails.setPermanentDataDetails(permanentItrDetails);
        torataxItrDetails.setYearlyDataDetails(yearlyItrDetails);
        torataxItrDetails.setUserId(itrRequest.getUserId());

        return torataxItrDetails;
    }

    private UserPermanentData saveUserPermanentData(ItrRequest itrRequest) {
        UserPermanentData savedPermanentData = userPermanentDataRepository.findByUserId(itrRequest.getUserId());
        if(savedPermanentData==null){
            UserPermanentData userPermanentData = extractUserPermanentData(itrRequest);
            String userFolder = rootFolderPath + File.separator + itrRequest.getUserId();
            RemoteFileToLocalFile.createDir(rootFolderPath, itrRequest.getUserId());
            userPermanentData.setFolderPath(userFolder);
            return userPermanentDataRepository.save(userPermanentData);
        }

        return savedPermanentData;
    }

    private UserYearlyInformation saveUserYearlyInformation(ItrRequest itrRequest, String userFolder) {
        String userYearlyFolder = userFolder + File.separator + itrRequest.getItrYear().toString();
        UserYearlyInformation savedData=userYearlyInformationRepository.findByUserIdAndYearlyFolderPath(itrRequest.getUserId(), userYearlyFolder);
        if(savedData==null) {
            UserYearlyInformation yearlyInformation = extractUserYearlyInformation(itrRequest);
            RemoteFileToLocalFile.createDir(userFolder, itrRequest.getItrYear().toString());
            yearlyInformation.setYearlyFolderPath(userYearlyFolder);
            return userYearlyInformationRepository.save(yearlyInformation);
        }
        return savedData;

    }
}
