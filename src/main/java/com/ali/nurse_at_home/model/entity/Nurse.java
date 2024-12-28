package com.ali.nurse_at_home.model.entity;

import com.ali.nurse_at_home.model.entity.address.Address;
import com.ali.nurse_at_home.model.enums.SearchRadius;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Table(name = "nurses")
@FieldDefaults(level = PRIVATE)
public class Nurse {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String firstname;

    String lastname;

    String diplomaUrl;

    String passportUrl;

    String photoUrl;

    @ManyToOne
    Address address;

    SearchRadius searchRadius;

    UUID userId;

    Boolean isAvailable;

    Boolean isVerified;

    Double rating;

    @OneToMany(mappedBy = "nurseId")
    List<Bid> bids;

    @OneToMany(mappedBy = "nurseId")
    List<NursePatientBlacklist> blackList;

    @ManyToMany
    @JoinTable(name = "nurses_procedures",
            joinColumns = @JoinColumn(name = "nurse_id"),
            inverseJoinColumns = @JoinColumn(name = "procedure_id"))
    List<Procedure> procedures;
}
