package com.ali.nurse_at_home.model.params;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class AddressParams {

    Long cityId;            // Город
    Long streetId;          // Улица
    String building;        // Номер дома
    int entrance;           // Номер подъезда
    int apartment;          // Номер квартиры
}
