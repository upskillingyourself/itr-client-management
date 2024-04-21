package com.toratax.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Setter
@Getter
public class ItrRequest {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailId;
    private String userId;
    private Year itrYear;
    private boolean salaried;
    private String pan;
}
