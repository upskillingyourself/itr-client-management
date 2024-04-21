package com.toratax.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "permanent_documents")
public class PermanentDocument {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_permanent_data_id", nullable = false)
    private UserPermanentData userPermanentData;

    @Column(name = "document_name", length = 100)
    private String documentName;

    @Column(name = "document_path", length = 100)
    private String documentPath;

    @Column(name = "uploaded_date", length = 100)
    private LocalDate uploadedDate;

    @Column(name = "document_type_id")
    private Integer documentTypeId;

}