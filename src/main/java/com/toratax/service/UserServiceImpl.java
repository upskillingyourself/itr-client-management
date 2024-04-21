package com.toratax.service;

import com.toratax.exception.DBException;
import com.toratax.model.dto.TorataxUser;

import static com.toratax.model.mapper.DomainModelToDto.torataxUserDomainToTorataxDomaainDto;
import static com.toratax.model.mapper.DtoToDomainModel.torataxUserDtoToTorataxDomain;

import com.toratax.repository.TorataxUserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private TorataxUserRepository torataxUserRepository;

    @Override
    public String registerUser(TorataxUser user) {
        com.toratax.model.domain.TorataxUser savedUser = null;

        com.toratax.model.domain.TorataxUser availableUser=
                torataxUserRepository.findByUserName(user.getUserName());
        if (availableUser!=null){
            throw new DBException(user.getUserName() + " user is already present in the system.");
        }

        String response = null;
        try {
            savedUser = torataxUserRepository.save(torataxUserDtoToTorataxDomain(user));
            if (savedUser.getId() > 0) {
                response = "Given user details are successfully registered";
            }
        } catch (DataAccessException ex) {
            log.info("Exception occur for user: {}", user.getUserName());
            throw ex;
        }
        return response;
    }

    @Override
    public TorataxUser userDetailsAfterLogin(String userName) {
        com.toratax.model.domain.TorataxUser user = null;
        try {
            user = torataxUserRepository.findByUserName(userName);
            if (user == null) {
                throw new DBException(userName + " user is not present in system.");
            }
        } catch (DataAccessException ex) {
            log.info("Exception occur for user: {}", userName);
            throw ex;
        }
        return torataxUserDomainToTorataxDomaainDto(user);
    }

    @Override
    public List<TorataxUser> listAllUsers() {
        List<TorataxUser> allUsers=null;
        List<com.toratax.model.domain.TorataxUser> domainAllUsers=null;
        try{
            domainAllUsers= (List<com.toratax.model.domain.TorataxUser>) torataxUserRepository.findAll();
            if(domainAllUsers==null){
                throw new DBException("No users available!");
            }
        }catch (DataAccessException ex){
            log.info("Exception occur to list users: {}");
            throw ex;
        }
        return domainAllUsers.stream().map(user-> torataxUserDomainToTorataxDomaainDto(user)).collect(Collectors.toList());
    }

}
