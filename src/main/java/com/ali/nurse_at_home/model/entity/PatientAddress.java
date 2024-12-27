package com.ali.nurse_at_home.model.entity;

import com.ali.nurse_at_home.model.entity.address.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "patients_addresses")
@FieldDefaults(level = PRIVATE)
public class PatientAddress {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @ManyToOne
    Patient patient;

    @ManyToOne(cascade = PERSIST)
    Address address;

    boolean isPrimary;

    public PatientAddress(Patient patient, Address address, boolean isPrimary) {
        this.patient = patient;
        this.address = address;
        this.isPrimary = isPrimary;
    }
}
