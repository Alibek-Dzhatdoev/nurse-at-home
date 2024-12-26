package com.ali.nurse_at_home.model.entity.address;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Table(name = "streets")
@FieldDefaults(level = PRIVATE)
public class Street {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;

    Long cityId;
}
