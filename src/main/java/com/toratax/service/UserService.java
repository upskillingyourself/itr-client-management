package com.toratax.service;

import com.toratax.model.dto.TorataxUser;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {
    String registerUser(TorataxUser user);

    TorataxUser userDetailsAfterLogin(String userName);

    @PreAuthorize("hasRole('ADMIN')")
    List<TorataxUser> listAllUsers();
}
