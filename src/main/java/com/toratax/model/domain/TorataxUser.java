package com.toratax.model.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Table(name = "toratax_user")
public class TorataxUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String LastName;

    @Column(name = "role_code", nullable = false)
    private String roleCode;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "user_id", nullable = false)
    private String userName;

    @Column(name = "phone_no")
    private String phoneNumber;

    private String password;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate= LocalDateTime.now();

    @Column(name = "end_date")
    private LocalDate endDate;


}
