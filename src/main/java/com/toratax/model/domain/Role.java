package com.toratax.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role", nullable = false, length = 100)
    private String id;

    @Column(name = "id", nullable = false)
    private Integer id2;

    @Column(name = "role_description", nullable = false, length = 200)
    private String roleDescription;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

}