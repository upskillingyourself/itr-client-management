package com.toratax.model.mapper;

import com.toratax.model.domain.UserPermanentData;
import com.toratax.model.domain.UserYearlyInformation;
import com.toratax.model.dto.ItrRequest;
import com.toratax.model.dto.TorataxUser;
import org.apache.commons.validator.routines.EmailValidator;

public class DtoToDomainModel {

    public static com.toratax.model.domain.TorataxUser torataxUserDtoToTorataxDomain(TorataxUser user) {
        com.toratax.model.domain.TorataxUser domainModel = new com.toratax.model.domain.TorataxUser();

        if (EmailValidator.getInstance().isValid(user.getUserName())) {
            domainModel.setEmailId(user.getEmailId());
        } else {
            domainModel.setPhoneNumber(user.getUserName());
        }
        domainModel.setFirstName(user.getFirstName());
        domainModel.setLastName(user.getLastName());
        domainModel.setUserName(user.getUserName());
        domainModel.setPassword(user.getPassword());
        domainModel.setRoleCode("user");
        return domainModel;
    }

    public static UserPermanentData extractUserPermanentData(ItrRequest itrData){
        UserPermanentData userPermanentData= new UserPermanentData();
        userPermanentData.setUserId(itrData.getUserId());
        userPermanentData.setPanNo(itrData.getPan());
        return userPermanentData;
    }
    public static UserYearlyInformation extractUserYearlyInformation(ItrRequest itrData){
        UserYearlyInformation yearlyInformation= new UserYearlyInformation();
        yearlyInformation.setUserId(itrData.getUserId());
        yearlyInformation.setItrYear(itrData.getItrYear());
        yearlyInformation.setSalaried(itrData.isSalaried()?1:0);
        return yearlyInformation;
    }
}
