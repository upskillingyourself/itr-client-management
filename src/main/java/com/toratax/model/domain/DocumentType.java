package com.toratax.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "document_type")
public class DocumentType {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false, length = 100)
    private String type;

    @Column(name = "ref_table_name", nullable = false, length = 100)
    private String referenceTable;

    private String description;

}