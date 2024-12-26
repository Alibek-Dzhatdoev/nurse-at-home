package com.ali.nurse_at_home.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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

    Long patientId;

    Long nurseId;

    String text;

    Integer communicationQuality;

    Integer serviceQuality;

    Integer arrivalRate;
}
