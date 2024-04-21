package com.toratax.model.mapper;

import com.toratax.model.dto.TorataxUser;

public class DomainModelToDto {

    public static TorataxUser torataxUserDomainToTorataxDomaainDto(com.toratax.model.domain.TorataxUser user) {
        TorataxUser userDto = new TorataxUser();
        userDto.setEmailId(user.getEmailId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUserName());
        userDto.setRole(user.getRoleCode());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }
}
