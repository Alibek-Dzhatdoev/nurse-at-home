package com.ali.nurse_at_home.model.entity;

import com.ali.nurse_at_home.model.enums.Initiator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "nurse_patient_blacklist")
public class NursePatientBlacklist {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    Long patientId;

    Long nurseId;

    @Enumerated(STRING)
    Initiator initiator;

    public NursePatientBlacklist(Long patientId, Long nurseId, Initiator initiator) {
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.initiator = initiator;
    }
}
