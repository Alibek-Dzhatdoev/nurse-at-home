package com.ali.nurse_at_home.model.entity.address;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Table(name = "addresses")
@FieldDefaults(level = PRIVATE)
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @ManyToOne
    com.ali.nurse_at_home.model.entity.address.Street street;

    @ManyToOne
    com.ali.nurse_at_home.model.entity.address.City city;

    String building;
    int apartment;
    int entrance;

    Double latitude;        // Географическая широта (для карт и навигации)
    Double longitude;
}
