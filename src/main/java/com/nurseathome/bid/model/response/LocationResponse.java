package com.nurseathome.bid.model.response;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class LocationResponse {

    Double longitude;

    Double latitude;
}