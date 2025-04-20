package com.nurseathome.bid.model.entity;

import com.nurseathome.bid.model.entity.address.Address;
import com.nurseathome.bid.model.enums.SearchRadius;
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
    SearchRadius searchRadius;
    UUID ssoUserId;
    Boolean isAvailable;
    Boolean isVerified;
    Boolean isActive;
    Double rating;

    @ManyToOne
    Address address;

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
