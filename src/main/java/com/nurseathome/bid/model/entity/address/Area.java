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
@Table(name = "areas")
@Accessors(chain = true)
@FieldDefaults(level = PRIVATE)
public class Area {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;
}
