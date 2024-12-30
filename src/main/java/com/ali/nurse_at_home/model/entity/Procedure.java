package com.ali.nurse_at_home.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Table(name = "procedures")
@FieldDefaults(level = PRIVATE)
public class Procedure {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    Double price;

    String name;

    String description;

    String imageUrl;

    Boolean isActive;
}
