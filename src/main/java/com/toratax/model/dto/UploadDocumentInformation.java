package com.toratax.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadDocumentInformation {
    private int typeId;
    private String documentTypeName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String filename;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String username;
}
