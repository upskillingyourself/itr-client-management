package com.toratax.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TorataxItrDetails {
    private ItrDetails permanentDataDetails;
    private ItrDetails yearlyDataDetails;
    private String userId;

}
