package com.ali.nurse_at_home.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

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

    String name;

    String diplomaUrl;

    @OneToMany(mappedBy = "nurseId")
    List<Review> reviews;

    @OneToMany(mappedBy = "nurseId")
    List<Bid> bids;
}
