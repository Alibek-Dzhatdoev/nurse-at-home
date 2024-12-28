package com.ali.nurse_at_home.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Table(name = "reviews")
@FieldDefaults(level = PRIVATE)
public class Review {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @OneToOne(fetch = LAZY, mappedBy = "reviewId")
    Bid bid;

    String text;

    Integer communicationQuality;

    Integer serviceQuality;

    Integer arrivalRate;
}
