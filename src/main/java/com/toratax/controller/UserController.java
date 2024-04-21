package com.toratax.controller;

import com.toratax.exception.DBException;
import com.toratax.model.dto.TorataxUser;
import com.toratax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.toratax.util.ServiceCommonValidations.isPhoneNumberOrMobileNumber;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/toratax/rest/v1.0/register")
    public ResponseEntity<String> userRegistration(@RequestBody TorataxUser user) {

        if(!isPhoneNumberOrMobileNumber(user.getUserName())){
            return new ResponseEntity<>("Username is neither mobile number nor a valid email id.", HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<String> response = null;
        try {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            String successMsg = userService.registerUser(user);
            if (successMsg != null) {
                response = new ResponseEntity<>("Given user details are successfully registered", HttpStatus.CREATED);

            }
        } catch (DBException | DataAccessException ex) {
            if (ex.getMessage().contains("already present")) {
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ex.getMessage());
            } else {
                response = ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ex.getMessage());
            }
        } catch (Exception ex) {

            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());

        }

        return response;
    }

    @GetMapping("/toratax/rest/v1.0/login")
    public ResponseEntity<TorataxUser> getUserDetailsAfterLogin(Authentication authentication) {
        TorataxUser user = null;
        ResponseEntity response = null;
        try {
            user = userService.userDetailsAfterLogin(authentication.getName());
        } catch (DBException | DataAccessException ex) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/toratax/rest/v1.0/users")
    public ResponseEntity<List<TorataxUser>> listAllUsers(){
        List<TorataxUser>users=null;
        ResponseEntity<?> response = null;
        try {
            users = userService.listAllUsers();
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body(users);
        } catch (DBException | DataAccessException ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}