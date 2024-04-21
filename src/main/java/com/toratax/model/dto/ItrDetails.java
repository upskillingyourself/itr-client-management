package com.toratax.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ItrDetails {

    private Integer dataId;
    private List<DocumentDetails> documentDetails;

}
