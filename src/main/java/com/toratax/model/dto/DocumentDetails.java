package com.toratax.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDetails {
    private Integer documentTypeId;
    private String documentName;
    private String documentPath;
    private String documentId;
}
