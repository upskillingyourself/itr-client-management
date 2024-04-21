package com.toratax.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "user_permanent_data")
public class  UserPermanentData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "aadhar_no",  length = 100)
    private String aadharNo;

    @Column(name = "user_id",  nullable = false)
    private String userId;

    @Column(name = "pan_no", nullable = false, length = 100)
    private String panNo;

    @Column(name = "passport_no", length = 100)
    private String passportNo;

    @Column(name = "driving_license", length = 100)
    private String drivingLicense;

    @Column(name = "voter_id", length = 100)
    private String voterId;

    @Column(name = "folder_path", nullable = false, length = 100)
    private String folderPath;

    @OneToMany(mappedBy = "userPermanentData", cascade = CascadeType.ALL)
    private Set<PermanentDocument> permanentDocuments;

}