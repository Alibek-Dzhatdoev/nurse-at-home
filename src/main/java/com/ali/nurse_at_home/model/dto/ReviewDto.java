package com.ali.nurse_at_home.model.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class ReviewDto {

    Long id;

    String text;

    Integer rate;

    LocalDate date;
}
