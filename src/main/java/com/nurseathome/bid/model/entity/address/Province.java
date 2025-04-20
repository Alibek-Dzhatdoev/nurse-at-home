package com.nurseathome.bid.model.entity.address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "provinces")
@FieldDefaults(level = PRIVATE)
public class Province {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;
}
