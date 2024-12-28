package com.ali.nurse_at_home.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "patients")
@FieldDefaults(level = PRIVATE)
public class Patient {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String email;
    String firstname;
    String lastname;
    String mobilePhone;
    LocalDate dateOfBirth;

    UUID userId;
    Boolean isActive;

    @OneToMany(mappedBy = "patient", cascade = ALL)
    List<PatientAddress> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "patientId")
    List<Bid> bids = new ArrayList<>();

    @OneToMany(mappedBy = "patientId")
    List<NursePatientBlacklist> blackList;
}
