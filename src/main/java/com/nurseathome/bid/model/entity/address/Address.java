package com.nurseathome.bid.model.entity.address;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "addresses")
@FieldDefaults(level = PRIVATE)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = EAGER, cascade = PERSIST)
    @JoinColumn(name = "country_id", nullable = false)
    Country country;

    @ManyToOne(fetch = EAGER, cascade = PERSIST)
    @JoinColumn(name = "province_id", nullable = false)
    Province province;

    @ManyToOne(fetch = EAGER, cascade = PERSIST)
    @JoinColumn(name = "area_id")
    Area area;

    @ManyToOne(fetch = EAGER, cascade = PERSIST)
    @JoinColumn(name = "locality_id", nullable = false)
    Locality locality;

    String street;
    String house;
    Integer entrance;
    Integer apartment;
    Integer floor;
    Integer intercom;
    Integer timezone;
    String geoUrl;

    Double latitude;
    Double longitude;
}
