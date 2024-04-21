package com.toratax.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_yearly_information")
public class UserYearlyInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "itr_year", nullable = false)
    private Year itrYear;

    @Column(name = "user_id",  nullable = false)
    private String userId;

    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    @Column(name = "payment_status", length = 100)
    private String paymentStatus;

    @Column(name = "salaried", nullable = false)
    private Integer salaried;

    @OneToMany(mappedBy ="yearlyInformation", cascade = CascadeType.ALL)
    private Set<YearlyDocument> yearlyDocuments;

    @Column(name = "yearly_folder_path", nullable = false, length = 100)
    private String yearlyFolderPath;

}