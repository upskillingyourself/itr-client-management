package com.toratax.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TorataxUser {
    @JsonProperty(required = true)
    private String userName;

    @JsonProperty(required = true)
    private String firstName;

    private String lastName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String emailId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String role;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, required = true)
    private String password;
}
