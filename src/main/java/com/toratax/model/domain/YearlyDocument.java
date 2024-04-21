package com.toratax.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "yearly_documents")
public class YearlyDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "yearly_information_id", nullable = false)
    private UserYearlyInformation yearlyInformation;

    @Column(name = "document_name", length = 100)
    private String documentName;

    @Column(name = "document_path", length = 100)
    private String documentPath;

    @Column(name = "uploaded_date", length = 100)
    private LocalDate uploadedDate;

    @Column(name = "document_type_id")
    private Integer documentTypeId;


}