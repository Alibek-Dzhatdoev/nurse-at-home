package com.ali.nurse_at_home.model.entity.address;

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
@Table(name = "cities")
@FieldDefaults(level = PRIVATE)
public class City {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;

    @OneToMany
    List<Street> streets;
}
